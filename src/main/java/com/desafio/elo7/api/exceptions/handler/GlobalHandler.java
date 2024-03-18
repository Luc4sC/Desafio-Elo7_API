package com.desafio.elo7.api.exceptions.handler;

import com.desafio.elo7.api.exceptions.InvalidCommandException;
import com.desafio.elo7.api.exceptions.InvalidNameException;
import com.desafio.elo7.api.exceptions.PlanetFullException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(InvalidCommandException.class)
    public ResponseEntity<Object> handlerInvalidCommandException(InvalidCommandException ex,
                                                                 HttpServletRequest request){
        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<Object> handlerInvalidCommandException(InvalidNameException ex,
                                                                 HttpServletRequest request){
        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(PlanetFullException.class)
    public ResponseEntity<Object> handlerInvalidCommandException(PlanetFullException ex,
                                                                 HttpServletRequest request){
        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
