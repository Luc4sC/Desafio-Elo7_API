package com.desafio.elo7.api.controllers;

import com.desafio.elo7.api.exceptions.PlanetFullException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class Test {

    @GetMapping
    public ResponseEntity<Object> test(){
        throw new PlanetFullException();
    }
}
