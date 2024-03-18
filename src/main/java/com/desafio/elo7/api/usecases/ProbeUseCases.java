package com.desafio.elo7.api.usecases;

import com.desafio.elo7.api.classes.Tools;
import com.desafio.elo7.api.classes.planet.Planet;
import com.desafio.elo7.api.classes.probe.Probe;
import com.desafio.elo7.api.classes.probe.ProbeDTO;
import com.desafio.elo7.api.classes.terminal.Terminal;
import com.desafio.elo7.api.exceptions.InvalidNameException;
import com.desafio.elo7.api.exceptions.PlanetFullException;
import com.desafio.elo7.api.exceptions.ProbeAlreadyExistException;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@AllArgsConstructor
public class ProbeUseCases implements Tools {
    private final PlanetUseCases planetUseCases;
    private final Terminal terminal;

    public String newProbe(@RequestBody ProbeDTO probeDTO, String planetID) throws ExecutionException, InterruptedException {
        if(verifyName(probeDTO.name())) throw new InvalidNameException();
        if(getProbeInPlanetByName(planetID, probeDTO.name()) != null) throw new ProbeAlreadyExistException(probeDTO.name());

        List<Probe> probes = getProbesByPlanet(planetID);
        if(probes.size() >= 25) throw new PlanetFullException();

        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference newProbe = database.collection("probes").document();

        Probe probe = Probe.builder().id(newProbe.getId()).name(probeDTO.name()).positionInX(0).positionInY(0).guidance(0).build();

        if(!probes.isEmpty()) terminal.landProbe(probe, probes);
        planetUseCases.updatePlanetProbesID(planetID, probe.getId());
        newProbe.set(probe);

        return probe.toString();
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
        return document.get().get().toObject(Probe.class);
    }

    public Probe getProbeInPlanetByName(String planetID, String name) throws ExecutionException, InterruptedException {
        Probe probe = null;
        List<Probe> probes = getProbesByPlanet(planetID);
        for(Probe probeX : probes){
            if(probeX.getName().equals(name)) probe = probeX;
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

    @Override
    public boolean verifyName(String name) {
        return name.contains(" ") || name.contains("/") || name.contains("\\") || name.length() > 20;
    }
}
