package com.desafio.elo7.api.controllers;

import com.desafio.elo7.api.classes.galaxy.Galaxy;
import com.desafio.elo7.api.classes.galaxy.GalaxyDTO;
import com.desafio.elo7.api.usecases.GalaxyUseCases;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("api/galaxy")
public class GalaxyController {

    private final GalaxyUseCases galaxyUseCases;

    @GetMapping(path = "get", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<Galaxy>> getGalaxies() throws ExecutionException, InterruptedException {
        List<Galaxy> response = galaxyUseCases.getGalaxies();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "post", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> postGalaxy(@RequestBody @Valid GalaxyDTO galaxyDTO){
        String response = galaxyUseCases.newGalaxy(galaxyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
