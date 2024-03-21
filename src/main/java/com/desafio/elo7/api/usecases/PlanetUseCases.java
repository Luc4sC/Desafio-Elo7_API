package com.desafio.elo7.api.usecases;

import com.desafio.elo7.api.entities.Galaxy;
import com.desafio.elo7.api.entities.Planet;
import com.desafio.elo7.api.dtos.PlanetDTO;
import com.desafio.elo7.api.exceptions.IDNotFoundException;
import com.desafio.elo7.api.exceptions.InvalidNameException;
import com.desafio.elo7.api.exceptions.PlanetAlreadyExistException;
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
public class PlanetUseCases {

    @Autowired
    private GalaxyUseCases galaxyUseCases;

    public Planet newPlanet(@RequestBody PlanetDTO planetDTO, String galaxyID) throws ExecutionException, InterruptedException {
        if(StringUtils.isValidText(planetDTO.name())) throw new InvalidNameException();
        if(getPlanetInGalaxyByName(galaxyID, planetDTO.name()) != null) throw new PlanetAlreadyExistException(planetDTO.name());

        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference newPlanet = database.collection("planets").document();
        Planet planet = Planet.builder().id(newPlanet.getId()).name(planetDTO.name()).build();
        newPlanet.set(planet);
        log.info("New Planet created");
        galaxyUseCases.updateGalaxyPlanetsID(galaxyID, planet.getId());

        return planet;
    }

    public Planet getPlanetByID(String id) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference document = database.collection("planets").document(id);
        Planet planet = document.get().get().toObject(Planet.class);
        if(planet == null) throw new IDNotFoundException(id, "Planet");
        log.info(document.toString());
        return planet;
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
            if(planetX.getName().equalsIgnoreCase(name)) planet = planetX;
        }
        return planet;
    }

    public void updatePlanetProbesID(String id, List<String> probesIDs) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference planetDoc = database.collection("planets").document(id);
        planetDoc.update("probesIDs", probesIDs);
        log.info(planetDoc.get().get().toObject(Planet.class) + " Uploaded");
    }
}
