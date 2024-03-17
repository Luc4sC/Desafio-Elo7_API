package com.desafio.elo7.api.controllers;

import com.desafio.elo7.api.classes.terminal.TerminalDTO;
import com.desafio.elo7.api.usecases.TerminalUseCases;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/terminal")
public class TerminalController {

    private TerminalUseCases terminalUseCases;

    @PostMapping(path = "{probeID}/move", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> moveProbe(@RequestBody TerminalDTO terminalDTO, @PathVariable String probeID) throws ExecutionException, InterruptedException {
        String response = terminalUseCases.setCommands(terminalDTO, probeID);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
