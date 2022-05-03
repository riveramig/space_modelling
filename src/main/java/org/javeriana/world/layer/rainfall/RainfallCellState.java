package org.javeriana.world.layer.rainfall;

import org.javeriana.automata.core.cell.LayerCellState;

public class RainfallCellState implements LayerCellState {

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
