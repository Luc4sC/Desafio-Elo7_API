package com.desafio.elo7.api.exceptions;

public class InvalidCommandException extends RuntimeException{
    public InvalidCommandException(){
        super("The allowed commands are: 'M' to move, 'L' to Turn Left and 'R' to Turn Right " +
                "Try type only this characters or insert under 100 commands");
    }
}
