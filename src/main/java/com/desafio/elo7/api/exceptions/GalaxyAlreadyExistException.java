package com.desafio.elo7.api.exceptions;

public class GalaxyAlreadyExistException extends RuntimeException{

    public GalaxyAlreadyExistException(String name){
        super("Galaxy with the name: " + name + " Already exists");
    }
}
