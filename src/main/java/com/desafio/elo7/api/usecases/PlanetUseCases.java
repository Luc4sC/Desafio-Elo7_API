package com.desafio.elo7.api.usecases;

import com.desafio.elo7.api.classes.planet.Planet;
import com.desafio.elo7.api.classes.planet.PlanetDTO;
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
public class PlanetUseCases {

    private final GalaxyUseCases galaxyUseCases;

    public String newPlanet(@RequestBody PlanetDTO planetDTO, String galaxyID) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference newPlanet = database.collection("planets").document();
        Planet planet = Planet.builder().id(newPlanet.getId()).name(planetDTO.name()).build();
        newPlanet.set(planet);
        log.info("New Planet created");
        galaxyUseCases.updateGalaxyPlanetsID(galaxyID);

        return planet.toString();
    }
}
