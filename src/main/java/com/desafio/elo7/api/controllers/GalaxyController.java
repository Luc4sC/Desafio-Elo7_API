package com.desafio.elo7.api.controllers;

import com.desafio.elo7.api.entities.Galaxy;
import com.desafio.elo7.api.dtos.GalaxyDTO;
import com.desafio.elo7.api.usecases.GalaxyUseCases;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/galaxy")
public class GalaxyController {

    @Autowired
    private GalaxyUseCases galaxyUseCases;

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<Galaxy>> getGalaxies() throws ExecutionException, InterruptedException {
        List<Galaxy> response = galaxyUseCases.getGalaxies();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<Galaxy> newGalaxy(@RequestBody @Valid GalaxyDTO galaxyDTO) throws ExecutionException, InterruptedException {
        Galaxy response = galaxyUseCases.newGalaxy(galaxyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
