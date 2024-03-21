package com.desafio.elo7.api.entities;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class Terminal {
    private final char[] commands = {'M', 'L', 'R'};
    private final List<String> guidances = Arrays.asList("North, ↑", "West, ←", "South, ↓", "East, →");

    public boolean verifyCommands(char[] commands){
        boolean b = true;
        for(char c : commands){
            if (c != 'M' && c != 'L' && c != 'R') {
                b = false;
                break;
            }
        }
        if(commands.length > 100 || commands.length == 0) b = false;
        return b;
    }

    public void moveProbe(Probe probe, char[] command){
        for (char c : command) {
            String currentGuidance = probe.getGuidance();
            int currentGuidanceInList = guidances.indexOf(currentGuidance);

            if (c == commands[0] && currentGuidanceInList == 0) {
                int newPositionInY = (probe.getPositionInY() + 1) % 5;
                probe.setPositionInY(newPositionInY);
            }

            if (c == commands[0] && currentGuidanceInList == 1) {
                int newPositionInX = (probe.getPositionInX() - 1) % 5;
                probe.setPositionInX(newPositionInX);
            }

            if (c == commands[0] && currentGuidanceInList == 2) {
                int newPositionInY = (probe.getPositionInY() - 1) % 5;
                probe.setPositionInY(newPositionInY);
            }

            if (c == commands[0] && currentGuidanceInList == 3) {
                int newPositionInX = (probe.getPositionInX() + 1) % 5;
                probe.setPositionInX(newPositionInX);
            }

            if (c == commands[1]) probe.setGuidance(guidances.get(currentGuidanceInList + 1));
            if (c == commands[2]) probe.setGuidance(guidances.get(currentGuidanceInList + 1));
        }
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
