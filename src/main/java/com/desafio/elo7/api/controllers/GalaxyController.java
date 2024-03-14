package com.desafio.elo7.api.controllers;

import com.desafio.elo7.api.classes.galaxy.GalaxyDTO;
import com.desafio.elo7.api.usecases.GalaxyUseCases;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/galaxy")
public class GalaxyController {

    private final GalaxyUseCases galaxyUseCases;
    @PostMapping(path = "/post", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> postGalaxy(@RequestBody @Valid GalaxyDTO galaxyDTO){
        String response = galaxyUseCases.newGalaxy(galaxyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
