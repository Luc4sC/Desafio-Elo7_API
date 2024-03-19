package com.desafio.elo7.api.usecases;

import com.desafio.elo7.api.classes.Tools;
import com.desafio.elo7.api.classes.galaxy.Galaxy;
import com.desafio.elo7.api.classes.galaxy.GalaxyDTO;
import com.desafio.elo7.api.exceptions.GalaxyAlreadyExistException;
import com.desafio.elo7.api.exceptions.IDNotFoundException;
import com.desafio.elo7.api.exceptions.InvalidNameException;
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
public class GalaxyUseCases implements Tools {

    public String newGalaxy(@RequestBody GalaxyDTO galaxyDTO) throws ExecutionException, InterruptedException {
        if(verifyName(galaxyDTO.name())) throw new InvalidNameException();
        if(getGalaxyByName(galaxyDTO.name()) != null) throw new GalaxyAlreadyExistException(galaxyDTO.name());

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

    public Galaxy getGalaxyByID(String id) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference document = database.collection("galaxies").document(id);
        Galaxy galaxy = document.get().get().toObject(Galaxy.class);
        if(galaxy == null) throw new IDNotFoundException(id, "Galaxy");
        log.info(document.toString());
        return galaxy;
    }

    public Galaxy getGalaxyByName(String name) throws ExecutionException, InterruptedException {
        Galaxy galaxy = null;
        List<Galaxy> galaxies = getGalaxies();
        for(Galaxy galaxyX : galaxies){
            if(galaxyX.getName().equals(name)) galaxy = galaxyX;
        }
        return galaxy;
    }

    public void updateGalaxyPlanetsID(String id, String planetID) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference galaxyDoc = database.collection("galaxies").document(id);
        Galaxy galaxy = galaxyDoc.get().get().toObject(Galaxy.class);
        if(galaxy != null){
            galaxy.addPlanetID(planetID);
            galaxyDoc.update("planetsIDs", galaxy.getPlanetsIDs());
            log.info(galaxy + " Uploaded");
        }
    }

    @Override
    public boolean verifyName(String name) {
        return name.contains(" ") || name.contains("/") || name.contains("\\") || name.length() > 20;
    }
}
