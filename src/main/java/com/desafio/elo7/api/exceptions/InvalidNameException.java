package com.desafio.elo7.api.exceptions;

public class InvalidNameException extends RuntimeException{
    public InvalidNameException(){
        super("Invalid Name, Try to type fewer than 20 characters or not Type '/' and '\\' ");
    }
}
