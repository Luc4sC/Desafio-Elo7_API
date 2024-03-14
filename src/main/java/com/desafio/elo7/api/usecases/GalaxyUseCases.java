package com.desafio.elo7.api.usecases;

import com.desafio.elo7.api.classes.galaxy.Galaxy;
import com.desafio.elo7.api.classes.galaxy.GalaxyDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
@AllArgsConstructor
public class GalaxyUseCases {

    public String newGalaxy(@RequestBody GalaxyDTO galaxyDTO){
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference newGalaxy = database.collection("galaxies").document();
        Galaxy galaxy = Galaxy.builder().id(newGalaxy.getId()).name(galaxyDTO.name()).build();
        newGalaxy.set(galaxy);
        log.info("New Galaxy created");

        return galaxy.toString();
    }
}
