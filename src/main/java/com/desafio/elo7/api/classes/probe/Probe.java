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
    private int[] position = new int[2];
    private int guidance;

    public int getPositionInX() {
        if(this.position[0] < 0) this.position[0] = this.position[0] * -4 % 5;
        return this.position[0];
    }

    public int getPositionInY() {
        if(this.position[1] < 0) this.position[1] = this.position[1]  * -4 % 5;
        return this.position[1];
    }

    public int getGuidance() {
        if(this.guidance < 0) this.guidance = this.guidance * -3 % 5;
        return this.guidance;
    }

    public void setPositionInX(int x){
        this.position[0] = x;
    }

    public void setPositionInY(int y) {
        this.position[1] = y;
    }

    @Override
    public String toString() {
        String[] guidances = {"North, ↑", "West, ←", "South, ↓", "East, →"};
        return "X = " + (getPositionInX() + 1)  + " Y = " + (getPositionInY() + 1) +
                " " + guidances[getGuidance()];
    }
}
