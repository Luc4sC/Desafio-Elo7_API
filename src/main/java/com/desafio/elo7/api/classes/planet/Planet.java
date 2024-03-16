package com.desafio.elo7.api.classes.planet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Planet {

    private String id;
    private String name;
    private final List<String> probesIDs = new ArrayList<>();

    public void addProbeID(String probeID){
        if(this.probesIDs.size() >= 25) throw new RuntimeException();
        probesIDs.add(probeID);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
