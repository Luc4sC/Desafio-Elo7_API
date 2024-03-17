package com.desafio.elo7.api.usecases;

import com.desafio.elo7.api.classes.probe.Probe;
import com.desafio.elo7.api.classes.terminal.Terminal;
import com.desafio.elo7.api.classes.terminal.TerminalDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@AllArgsConstructor
public class TerminalUseCases {

    private ProbeUseCases probeUseCases;
    private Terminal terminal;
    public String setCommands(@RequestBody TerminalDTO terminalDTO, String probeID) throws ExecutionException, InterruptedException {
        char[] commands = terminalDTO.commands().toUpperCase().toCharArray();
        if(!terminal.verifyCommands(commands)) throw new RuntimeException();

        Probe probe = probeUseCases.getProbeByID(probeID);
        terminal.moveProbe(probe, commands);
        probeUseCases.updateProbePosition(probe);
        return "Probe has been moved Successfully " + probe;
    }
}