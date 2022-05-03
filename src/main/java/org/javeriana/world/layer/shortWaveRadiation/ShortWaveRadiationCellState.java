package org.javeriana.world.layer.shortWaveRadiation;

import org.javeriana.automata.core.cell.LayerCellState;

public class ShortWaveRadiationCellState implements LayerCellState {

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
