package com.desafio.elo7.api.classes.terminal;

import com.desafio.elo7.api.classes.planet.Planet;
import com.desafio.elo7.api.classes.probe.Probe;
import com.desafio.elo7.api.usecases.ProbeUseCases;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
public class Terminal {
    private final char[] commands = {'M', 'L', 'R'};
    private final String[] guidances = {"North, ↑", "West, ←", "South, ↓", "East, →"};
    private final ProbeUseCases probeUseCases;

    public Probe moveProbe(Probe probe, char[] command){
        for (char c : command) {
            if (c == commands[0] && guidances[probe.getGuidance()].equals(guidances[0])) {
                int newPositionInY = (probe.getPositionInY() + 1) % 5;
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

    public boolean isPositionEmpty(int[] position, List<Probe> probes){
        boolean b = true;
        for(Probe probe : probes){
            if(position == probe.getPosition()) {
                b = false;
                break;
            }
        }
        return b;
    }

    public int[] moveProbeToEmptyPosition(Probe probe, List<Probe> probes){
        boolean b = isPositionEmpty(probe.getPosition(), probes);
        while(!b){
            for(int i = 0; i < 5; i++){
                int newPositionInX = (probe.getPositionInX() + 1) % 5;
                probe.setPositionInX(newPositionInX);
                for(int x = 0; x < 5; x++){
                    int newPositionInY = (probe.getPositionInY() + 1) % 5;
                    probe.setPositionInY(newPositionInY);
                }
            }
            b = isPositionEmpty(probe.getPosition(), probes);
        }
        return probe.getPosition();
    }

    public Probe landProbe(Probe probe, Planet planet) throws ExecutionException, InterruptedException {
        List<String> probeIDList = planet.getProbesIDs();
        probeIDList.remove(probe.getId());

        List<Probe> probes = new ArrayList<>();
        for(String id : probeIDList){
            Probe p = probeUseCases.getProbeByID(id);
            probes.add(p);
        }


        return probe;
    }
}
