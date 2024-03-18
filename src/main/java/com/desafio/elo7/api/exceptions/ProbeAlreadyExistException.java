package com.desafio.elo7.api.exceptions;

public class ProbeAlreadyExistException extends RuntimeException {
    public ProbeAlreadyExistException(String name){
        super("Probe with the name: " + name + " Already exists");
    }
}
