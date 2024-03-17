package com.desafio.elo7.api.controllers;

import com.desafio.elo7.api.classes.probe.ProbeDTO;
import com.desafio.elo7.api.usecases.ProbeUseCases;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/probe")
public class ProbeController {

    private final ProbeUseCases probeUseCases;
    @PostMapping(path = "{planetID}/post", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> postProbe(@RequestBody ProbeDTO probeDTO, @PathVariable String planetID) throws ExecutionException, InterruptedException {
        String response = probeUseCases.newProbe(probeDTO,planetID);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
