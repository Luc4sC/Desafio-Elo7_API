package com.desafio.elo7.api.usecases;

import com.desafio.elo7.api.classes.galaxy.Galaxy;
import com.desafio.elo7.api.classes.galaxy.GalaxyDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
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
public class GalaxyUseCases {

    public String newGalaxy(@RequestBody GalaxyDTO galaxyDTO){
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference newGalaxy = database.collection("galaxies").document();
        Galaxy galaxy = Galaxy.builder().id(newGalaxy.getId()).name(galaxyDTO.name()).build();
        newGalaxy.set(galaxy);
        log.info("New Galaxy created");

        return galaxy.toString();
    }

    public List<Galaxy> getGalaxies() throws ExecutionException, InterruptedException {
        List<Galaxy> galaxies = new ArrayList<>();
        final Firestore database = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> galaxyList = database.collection("galaxies").get();
        List<QueryDocumentSnapshot> documents = galaxyList.get().getDocuments();
        for(QueryDocumentSnapshot document : documents){
            galaxies.add(document.toObject(Galaxy.class));
        }

        return galaxies;
    }

    public Galaxy getGalaxyByName(String name) throws ExecutionException, InterruptedException {
        List<Galaxy> galaxies = getGalaxies();
        Galaxy galaxySearched = Galaxy.builder().build();
        for (Galaxy galaxy : galaxies) {
            if (galaxy.getName().equals(name)) {
                galaxySearched = galaxy;
                break;
            }
        }

        return galaxySearched;
    }
}
