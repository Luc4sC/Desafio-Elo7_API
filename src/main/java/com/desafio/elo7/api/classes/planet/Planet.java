package com.desafio.elo7.api.classes.planet;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Planet {

    private String id;
    private String name;
    private final List<String> probesIDs = new ArrayList<>();

    public void addProbeID(String probeID){
        probesIDs.add(probeID);
    }

    public void removeProbeID(String probeID){
        probesIDs.remove(probeID);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
