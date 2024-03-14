package com.desafio.elo7.api.classes.planet;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Planet {

    private String id;
    private String name;
}
