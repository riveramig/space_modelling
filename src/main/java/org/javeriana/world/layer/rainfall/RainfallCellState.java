package org.javeriana.world.layer.rainfall;

import org.javeriana.automata.core.cell.CellState;

public class RainfallCellState implements CellState {

    private double rainfall;

    public RainfallCellState(double rainfall) {
        this.rainfall = rainfall;
    }

    public double getRainfall() {
        return rainfall;
    }

    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }
}
