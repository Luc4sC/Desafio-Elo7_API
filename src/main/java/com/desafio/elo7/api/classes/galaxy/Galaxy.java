package com.desafio.elo7.api.classes.galaxy;

import com.desafio.elo7.api.classes.planet.Planet;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
public class Galaxy {
    private String id;
    private String name;
    private final List<String> idsPlanets = new ArrayList<>();

    public void addIdPlanets(String idPlanet){
        idsPlanets.add(idPlanet);
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
