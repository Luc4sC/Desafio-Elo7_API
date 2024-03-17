package com.desafio.elo7.api.classes.probe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Probe {

    private String id;
    private String name;
    private int positionInX;
    private int positionInY;
    private int guidance;

    public int getPositionInX() {
        if(this.positionInX < 0) this.positionInX = this.positionInX * -4 % 5;
        return this.positionInX;
    }

    public int getPositionInY() {
        if(this.positionInY < 0) this.positionInY = this.positionInY  * -4 % 5;
        return this.positionInY;
    }

    public int getGuidance(){
        if(this.guidance < 0) this.guidance = this.guidance * -3 % 4;
        return this.guidance;
    }

    @Override
    public String toString() {
        String[] guidances = {"North, ↑", "West, ←", "South, ↓", "East, →"};
        return this.getName() + ": X = " + (this.getPositionInX() + 1)  + " Y = " + (this.getPositionInY() + 1) +
                " Guidance: " + guidances[this.getGuidance()];
    }
}