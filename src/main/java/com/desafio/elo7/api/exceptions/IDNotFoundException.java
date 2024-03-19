package com.desafio.elo7.api.exceptions;

public class IDNotFoundException extends RuntimeException{

    public IDNotFoundException(String id, String object){
        super("The " + object + " of ID: " + id + " wasn't found");
    }
}
