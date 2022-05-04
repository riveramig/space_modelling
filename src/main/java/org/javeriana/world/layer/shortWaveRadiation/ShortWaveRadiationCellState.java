package org.javeriana.world.layer.shortWaveRadiation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.automata.core.cell.LayerCellState;

public class ShortWaveRadiationCellState implements LayerCellState {

    private static final Logger logger = LogManager.getLogger(ShortWaveRadiationCellState.class);
    private double shortWaveRadiation;


    public ShortWaveRadiationCellState(double shortWaveRadiation) {
        logger.info("New short wave radiation state: " + shortWaveRadiation);
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
