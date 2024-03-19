package com.desafio.elo7.api.usecases;

import com.desafio.elo7.api.classes.Tools;
import com.desafio.elo7.api.classes.galaxy.Galaxy;
import com.desafio.elo7.api.classes.planet.Planet;
import com.desafio.elo7.api.classes.planet.PlanetDTO;
import com.desafio.elo7.api.exceptions.InvalidNameException;
import com.desafio.elo7.api.exceptions.PlanetAlreadyExistException;
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
public class PlanetUseCases implements Tools {

    private final GalaxyUseCases galaxyUseCases;

    public String newPlanet(@RequestBody PlanetDTO planetDTO, String galaxyID) throws ExecutionException, InterruptedException {
        if(verifyName(planetDTO.name())) throw new InvalidNameException();
        if(getPlanetInGalaxyByName(galaxyID, planetDTO.name()) != null) throw new PlanetAlreadyExistException(planetDTO.name());

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

    public Planet getPlanetInGalaxyByName(String galaxyID, String name) throws ExecutionException, InterruptedException {
        Planet planet = null;
        List<Planet> planets = getPlanetsByGalaxy(galaxyID);
        for(Planet planetX : planets){
            if(planetX.getName().equals(name)) planet = planetX;
        }
        return planet;
    }

    public void updatePlanetProbesID(String id, List<String> probesIDs) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference planetDoc = database.collection("planets").document(id);
        planetDoc.update("probesIDs", probesIDs);
        log.info(planetDoc.get().get().toObject(Planet.class) + " Uploaded");
    }

    @Override
    public boolean verifyName(String name) {
        return name.contains(" ") || name.contains("/") || name.contains("\\") || name.length() > 20;
    }
}
