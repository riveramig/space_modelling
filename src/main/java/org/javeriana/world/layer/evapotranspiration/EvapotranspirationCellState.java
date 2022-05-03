package org.javeriana.world.layer.evapotranspiration;

import org.javeriana.automata.core.cell.LayerCellState;

public class EvapotranspirationCellState implements LayerCellState {


    private double evapotranspirationReference;

    public EvapotranspirationCellState(double evapotranspirationReference) {
        this.evapotranspirationReference = evapotranspirationReference;
    }

    public EvapotranspirationCellState(){}

    public double getEvapotranspirationReference() {
        return evapotranspirationReference;
    }

    public void setEvapotranspirationReference(double evapotranspirationReference) {
        this.evapotranspirationReference = evapotranspirationReference;
    }

}
