package org.javeriana.world.layer.temperature;

import org.javeriana.automata.core.cell.CellState;

public class TemperatureCellState implements CellState {

    private double temperature;


    public TemperatureCellState(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
