package com.desafio.elo7.api.usecases;

import com.desafio.elo7.api.classes.galaxy.Galaxy;
import com.desafio.elo7.api.classes.planet.Planet;
import com.desafio.elo7.api.classes.planet.PlanetDTO;
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
public class PlanetUseCases {

    private final GalaxyUseCases galaxyUseCases;

    public String newPlanet(@RequestBody PlanetDTO planetDTO, String galaxyID) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference newPlanet = database.collection("planets").document();
        Planet planet = Planet.builder().id(newPlanet.getId()).name(planetDTO.name()).build();
        newPlanet.set(planet);
        log.info("New Planet created");
        galaxyUseCases.updateGalaxyPlanetsID(galaxyID, planet.getId());

        return planet.toString();
    }

    public Planet getPlanetByID(String id) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference document = database.collection("planets").document(id);

        return document.get().get().toObject(Planet.class);
    }

    public List<Planet> getPlanetsByGalaxy(String galaxyID) throws ExecutionException, InterruptedException {
        List<Planet> planets = new ArrayList<>();
        Galaxy galaxy = galaxyUseCases.getGalaxyByID(galaxyID);
        List<String> planetsIDs = galaxy.getPlanetsIDs();
        log.info(planetsIDs.toString());
        for (String id : planetsIDs) {
            Planet planet = getPlanetByID(id);
            planets.add(planet);
        }

        return planets;
    }

    public void updatePlanetProbesID(String id, String probeID) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference planetDoc = database.collection("planets").document(id);
        Planet planet = planetDoc.get().get().toObject(Planet.class);
        if(planet != null){
            planet.addProbeID(probeID);
            planetDoc.update("probesIDs", planet.getProbesIDs());
            log.info(planet + " Uploaded");
        }
    }
}
