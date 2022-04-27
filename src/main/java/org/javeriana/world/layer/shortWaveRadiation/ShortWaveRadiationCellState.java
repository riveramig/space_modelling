package org.javeriana.world.layer.shortWaveRadiation;

import org.javeriana.automata.core.cell.CellState;

public class ShortWaveRadiationCellState implements CellState {

    private double shortWaveRadiation;


    public ShortWaveRadiationCellState(double shortWaveRadiation) {
        this.shortWaveRadiation = shortWaveRadiation;
    }

    public ShortWaveRadiationCellState() {
    }

    public double getShortWaveRadiation() {
        return shortWaveRadiation;
    }

    public void setShortWaveRadiation(double shortWaveRadiation) {
        this.shortWaveRadiation = shortWaveRadiation;
    }
}
