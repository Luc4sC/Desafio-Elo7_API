package com.desafio.elo7.api.controllers;

import com.desafio.elo7.api.dtos.TerminalDTO;
import com.desafio.elo7.api.entities.Probe;
import com.desafio.elo7.api.dtos.ProbeDTO;
import com.desafio.elo7.api.usecases.ProbeUseCases;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "api/probe")
public class ProbeController {

    @Autowired
    private ProbeUseCases probeUseCases;

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<Probe>> getProbesByPlanet(@RequestParam String planetID) throws ExecutionException, InterruptedException {
        List<Probe> response = probeUseCases.getProbesByPlanet(planetID);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "{probeID}", produces = "application/json; charset=utf-8")
    public ResponseEntity<Probe> getProbe(@PathVariable String probeID) throws ExecutionException, InterruptedException {
        Probe response = probeUseCases.getProbeByID(probeID);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "{planetID}/new", produces = "application/json; charset=utf-8")
    public ResponseEntity<Probe> newProbe(@RequestBody @Valid ProbeDTO probeDTO, @PathVariable String planetID) throws ExecutionException, InterruptedException {
        Probe response = probeUseCases.newProbe(probeDTO,planetID);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(path = "{oldPlanetID}/{probeID}/{newPlanetID}/change")
    public ResponseEntity<Probe> changePlanetProbe(@PathVariable String oldPlanetID, @PathVariable String probeID,
                                           @PathVariable String newPlanetID) throws ExecutionException, InterruptedException {
        Probe response = probeUseCases.changeProbeOfPlanet(oldPlanetID, probeID, newPlanetID);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "{planetID}/{probeID}/move", produces = "application/json; charset=utf-8")
    public ResponseEntity<Probe> moveProbe(@RequestBody TerminalDTO terminalDTO, @PathVariable String planetID, @PathVariable String probeID) throws ExecutionException, InterruptedException {
        Probe response = probeUseCases.setCommands(terminalDTO, planetID, probeID);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "{planetID}/{probeID}/delete")
    public ResponseEntity<Probe> deleteProbe(@PathVariable String planetID, @PathVariable String probeID) throws ExecutionException, InterruptedException {
        Probe response = probeUseCases.deleteProbe(planetID, probeID);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
