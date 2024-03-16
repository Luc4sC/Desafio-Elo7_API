package com.desafio.elo7.api.usecases;

import com.desafio.elo7.api.classes.galaxy.Galaxy;
import com.desafio.elo7.api.classes.probe.Probe;
import com.desafio.elo7.api.classes.probe.ProbeDTO;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@AllArgsConstructor
public class ProbeUseCases {
    private final PlanetUseCases planetUseCases;

    public String newProbe(@RequestBody ProbeDTO probeDTO, String planetID){
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference newProbe = database.collection("probes").document();
        int[] position = {probeDTO.positionInX(), probeDTO.positionInY()};
        Probe probe = Probe.builder().id(newProbe.getId()).name(probeDTO.name())
                .position(position).build();

        return probe.toString();
    }

    public Probe getProbeByID(String id) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference document = database.collection("probes").document(id);
        log.info(document.toString());
        return document.get().get().toObject(Probe.class);
    }
}
