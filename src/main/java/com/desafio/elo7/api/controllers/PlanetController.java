package com.desafio.elo7.api.controllers;

import com.desafio.elo7.api.classes.planet.PlanetDTO;
import com.desafio.elo7.api.usecases.PlanetUseCases;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("api/planet")
public class PlanetController {

    private final PlanetUseCases planetUseCases;
    @PostMapping(path = "{galaxyID}/post", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> postPlanet(@RequestBody PlanetDTO planetDTO, @PathVariable String galaxyID) throws ExecutionException, InterruptedException {
        String response = planetUseCases.newPlanet(planetDTO, galaxyID);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
