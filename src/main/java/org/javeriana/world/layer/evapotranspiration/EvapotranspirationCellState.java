package org.javeriana.world.layer.evapotranspiration;

import org.javeriana.automata.core.cell.CellState;

public class EvapotranspirationCellState implements CellState {


    private double currentEvapotranspirationReference;

    public EvapotranspirationCellState(double evapotranspirationReference) {
        this.currentEvapotranspirationReference = evapotranspirationReference;
    }

    public EvapotranspirationCellState(){}

    public double getCurrentEvapotranspirationReference() {
        return currentEvapotranspirationReference;
    }

    public void setCurrentEvapotranspirationReference(double evapotranspirationReference) {
        this.currentEvapotranspirationReference = evapotranspirationReference;
    }

}
