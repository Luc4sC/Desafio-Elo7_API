package com.desafio.elo7.api.exceptions;

public class PlanetFullException extends RuntimeException{
    public PlanetFullException(){
        super("The planet already has 25 probes Try to found another planet to this new Probe");
    }
}
