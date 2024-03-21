package com.desafio.elo7.api.exceptions;

import com.desafio.elo7.api.entities.Planet;
import com.desafio.elo7.api.entities.Probe;

public class ProbeIsNotInThePlanetException extends RuntimeException{

    public ProbeIsNotInThePlanetException(Planet planet, Probe probe){
        super("The Probe: " + probe + " isn't in the Planet: " + planet);
    }
}
