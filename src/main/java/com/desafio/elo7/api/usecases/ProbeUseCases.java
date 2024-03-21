package com.desafio.elo7.api.usecases;

import com.desafio.elo7.api.dtos.TerminalDTO;
import com.desafio.elo7.api.entities.Terminal;
import com.desafio.elo7.api.entities.Planet;
import com.desafio.elo7.api.entities.Probe;
import com.desafio.elo7.api.dtos.ProbeDTO;
import com.desafio.elo7.api.exceptions.*;
import com.desafio.elo7.api.utills.StringUtils;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class ProbeUseCases {

    @Autowired
    private PlanetUseCases planetUseCases;
    @Autowired
    private Terminal terminal;

    public Probe newProbe(@RequestBody ProbeDTO probeDTO, String planetID) throws ExecutionException, InterruptedException {
        if(StringUtils.isValidText(probeDTO.name())) throw new InvalidNameException();
        if(getProbeInPlanetByName(planetID, probeDTO.name()) != null) throw new ProbeAlreadyExistException(probeDTO.name());

        List<Probe> probes = getProbesByPlanet(planetID);
        if(probes.size() >= 25) throw new PlanetFullException();

        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference newProbe = database.collection("probes").document();

        Probe probe = Probe.builder().id(newProbe.getId()).name(probeDTO.name()).positionInX(0).positionInY(0).guidance("North, ↑").build();

        if(!probes.isEmpty()) terminal.landProbe(probe, probes);
        Planet planet = planetUseCases.getPlanetByID(planetID);
        planet.addProbeID(probe.getId());
        planetUseCases.updatePlanetProbesID(planetID, planet.getProbesIDs());
        newProbe.set(probe);

        return probe;
    }

    public List<Probe> getProbesByPlanet(String planetID) throws ExecutionException, InterruptedException {
        List<Probe> probes = new ArrayList<>();
        Planet planet = planetUseCases.getPlanetByID(planetID);
        for(String id : planet.getProbesIDs()){
            Probe probe = getProbeByID(id);
            probes.add(probe);
        }
        return probes;
    }

    public Probe getProbeByID(String id) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference document = database.collection("probes").document(id);
        Probe probe = document.get().get().toObject(Probe.class);
        if(probe == null) throw new IDNotFoundException(id, "Probe");
        log.info(document.toString());
        return probe;
    }

    public Probe getProbeInPlanetByName(String planetID, String name) throws ExecutionException, InterruptedException {
        Probe probe = null;
        List<Probe> probes = getProbesByPlanet(planetID);
        for(Probe probeX : probes){
            if(probeX.getName().equalsIgnoreCase(name)) probe = probeX;
        }
        return probe;
    }

    public void updateProbePosition(Probe probe){
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference probeDoc = database.collection("probes").document(probe.getId());
        probeDoc.update("positionInX", probe.getPositionInX(),
                "positionInY", probe.getPositionInY(),
                "guidance", probe.getGuidance());
        log.info(probe + " Uploaded");
    }

    public Probe changeProbeOfPlanet(String oldPlanetID, String probeID, String newPlanetID) throws ExecutionException, InterruptedException {
        Probe probe = getProbeByID(probeID);

        Planet oldPlanet = planetUseCases.getPlanetByID(oldPlanetID);
        Planet newPlanet = planetUseCases.getPlanetByID(newPlanetID);

        if(newPlanet.getProbesIDs().size() >= 25) throw new PlanetFullException();
        if(getProbeInPlanetByName(newPlanetID, probe.getName()) != null) throw new ProbeAlreadyExistException(probe.getName());

        terminal.landProbe(probe, getProbesByPlanet(newPlanetID));

        oldPlanet.removeProbeID(probeID);
        planetUseCases.updatePlanetProbesID(oldPlanetID, oldPlanet.getProbesIDs());
        newPlanet.addProbeID(probeID);
        planetUseCases.updatePlanetProbesID(newPlanetID, newPlanet.getProbesIDs());

        return probe;
    }

    public Probe setCommands(@RequestBody TerminalDTO terminalDTO, String planetID, String probeID) throws ExecutionException, InterruptedException {
        char[] commands = terminalDTO.commands().toUpperCase().toCharArray();
        if(!terminal.verifyCommands(commands)) throw new InvalidCommandException();

        Probe probe = getProbeByID(probeID);
        List<Probe> probes = getProbesByPlanet(planetID);
        log.info(probes.toString());
        probes.remove(probe);
        log.info(probes.toString());

        terminal.moveProbe(probe, commands);
        if(!terminal.isPositionEmpty(probe.getPositionInX(), probe.getPositionInY(), probes))
            terminal.moveProbeToEmptyPosition(probe, probes);

        updateProbePosition(probe);
        return probe;
    }

    public Probe deleteProbe(String planetID,String probeID) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference probeDoc = database.collection("probes").document(probeID);
        Probe probe = getProbeByID(probeID);
        probeDoc.delete();

        Planet planet = planetUseCases.getPlanetByID(planetID);
        planet.removeProbeID(probeID);

        planetUseCases.updatePlanetProbesID(planetID, planet.getProbesIDs());

        return probe;
    }
}
