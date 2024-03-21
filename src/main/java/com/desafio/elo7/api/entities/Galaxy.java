package com.desafio.elo7.api.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Galaxy {
    private String id;
    private String name;
    private final List<String> planetsIDs = new ArrayList<>();

    public void addPlanetID(String idPlanet){
        planetsIDs.add(idPlanet);
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
