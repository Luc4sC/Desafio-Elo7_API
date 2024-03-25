package com.desafio.elo7.api.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Probe {

    private String id;
    private String name;
    private int positionInX;
    private int positionInY;
    private String guidance;

    public int getPositionInX() {
        if(this.positionInX == 0) this.positionInX = 5;
        if(this.positionInX < 0) this.positionInX = this.positionInX * -5 % 5;

        return this.positionInX;
    }

    public int getPositionInY() {
        if(this.positionInY == 0) this.positionInY = 5;
        if(this.positionInY < 0) this.positionInY = this.positionInY * -5 % 5;
        return this.positionInY;
    }

    @Override
    public String toString() {
        return "Probe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", positionInX=" + positionInX +
                ", positionInY=" + positionInY +
                ", guidance=" + guidance +
                '}';
    }
}
