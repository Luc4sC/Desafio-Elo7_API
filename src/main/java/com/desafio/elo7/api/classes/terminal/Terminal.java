package com.desafio.elo7.api.classes.terminal;

import com.desafio.elo7.api.classes.probe.Probe;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Terminal {
    private final char[] commands = {'M', 'L', 'R'};
    private final String[] guidances = {"North, ↑", "West, ←", "South, ↓", "East, →"};

    public Probe moveProbe(Probe probe, char[] command){
        for (char c : command) {
            if (c == commands[0] && guidances[probe.getGuidance()].equals(guidances[0])) {
                int newPositionInY = (probe.getPositionInY()  + 1) % 5;
                probe.setPositionInY(newPositionInY);
            }

            if (c == commands[0] && guidances[probe.getGuidance()].equals(guidances[1])) {
                int newPositionInX = (probe.getPositionInX() - 1) % 5;
                probe.setPositionInX(newPositionInX);
            }

            if (c == commands[0] && guidances[probe.getGuidance()].equals(guidances[2])) {
                int newPositionInY = (probe.getPositionInY() - 1) % 5;
                probe.setPositionInY(newPositionInY);
            }

            if (c == commands[0] && guidances[probe.getGuidance()].equals(guidances[3])) {
                int newPositionInX = (probe.getPositionInX() + 1) % 5;
                probe.setPositionInX(newPositionInX);
            }

            if (c == commands[1]) probe.setGuidance((probe.getGuidance() + 1) % 4);
            if (c == commands[2]) probe.setGuidance((probe.getGuidance() - 1) % 4);
        }
        return probe;
    }

    public boolean isPositionEmpty(int positionInX, int positionInY, List<Probe> probes){
        boolean b = true;
        for(Probe probe : probes){
            if(positionInX == probe.getPositionInX() && positionInY == probe.getPositionInY()) {
                b = false;
                break;
            }
        }
        return b;
    }

    public void moveProbeToEmptyPosition(Probe probe, List<Probe> probes){
        while(!isPositionEmpty(probe.getPositionInX(), probe.getPositionInY(), probes)){
            int newPositionInX = (probe.getPositionInX() + 1) % 5;
            probe.setPositionInX(newPositionInX);
            if(isPositionEmpty(probe.getPositionInX(), probe.getPositionInY(), probes)) break;
            for(int x = 0; x < 5; x++){
                int newPositionInY = (probe.getPositionInY() + 1) % 5;
                probe.setPositionInY(newPositionInY);
                if(isPositionEmpty(probe.getPositionInX(), probe.getPositionInY(), probes)) break;
            }
        }
    }

    public void landProbe(Probe probe, List<Probe> probes) {
        while(!isPositionEmpty(probe.getPositionInX(), probe.getPositionInY(), probes)) moveProbeToEmptyPosition(probe, probes);
    }
}
