package com.desafio.elo7.api.exceptions.handler;

import com.desafio.elo7.api.exceptions.*;
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

    @ExceptionHandler(GalaxyAlreadyExistException.class)
    public ResponseEntity<Object> handlerGalaxyAlreadyExistException(GalaxyAlreadyExistException ex,
                                                                     HttpServletRequest request){

        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(PlanetAlreadyExistException.class)
    public ResponseEntity<Object> handlerPlanetAlreadyExistException(PlanetAlreadyExistException ex,
                                                                     HttpServletRequest request){

        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ProbeAlreadyExistException.class)
    public ResponseEntity<Object> handlerProbeAlreadyExistException(ProbeAlreadyExistException ex,
                                                                    HttpServletRequest request){
        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IDNotFoundException.class)
    public ResponseEntity<Object> handlerIDNotFoundException(IDNotFoundException ex, HttpServletRequest request){
        DefaultError error = new DefaultError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
