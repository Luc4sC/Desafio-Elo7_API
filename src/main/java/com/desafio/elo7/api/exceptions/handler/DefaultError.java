package com.desafio.elo7.api.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultError {
    private int status;
    private String message;
}
