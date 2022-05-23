package org.javeriana.world.layer.rainfall;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.automata.core.cell.LayerCellState;

/**
 * Concrete implementation of the rainfall cell state
 */
public class RainfallCellState implements LayerCellState {

    private static final Logger logger = LogManager.getLogger(RainfallCellState.class);
    private double rainfall;

    public RainfallCellState(double rainfall) {
        logger.info("New rainfall state: " + rainfall);
        this.rainfall = rainfall;
    }

    public double getRainfall() {
        return rainfall;
    }

    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }
}
