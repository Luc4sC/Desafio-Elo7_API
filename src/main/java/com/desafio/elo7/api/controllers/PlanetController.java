package com.desafio.elo7.api.controllers;

import com.desafio.elo7.api.entities.Planet;
import com.desafio.elo7.api.dtos.PlanetDTO;
import com.desafio.elo7.api.usecases.PlanetUseCases;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/planet")
public class PlanetController {

    @Autowired
    private PlanetUseCases planetUseCases;
    @GetMapping(path = "{galaxyID}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<Planet>> getPlanetsByGalaxy(@PathVariable String galaxyID) throws ExecutionException, InterruptedException {
        List<Planet> response = planetUseCases.getPlanetsByGalaxy(galaxyID);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "{galaxyID}", produces = "application/json; charset=utf-8")
    public ResponseEntity<Planet> newPlanet(@RequestBody @Valid PlanetDTO planetDTO, @PathVariable String galaxyID) throws ExecutionException, InterruptedException {
        Planet response = planetUseCases.newPlanet(planetDTO, galaxyID);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
