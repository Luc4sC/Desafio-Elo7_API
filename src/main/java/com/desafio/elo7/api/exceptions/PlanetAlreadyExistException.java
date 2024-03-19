package com.desafio.elo7.api.exceptions;

public class PlanetAlreadyExistException extends RuntimeException{

    public PlanetAlreadyExistException(String name){
        super("Planet with the name: " + name + " Already exists");
    }
}
