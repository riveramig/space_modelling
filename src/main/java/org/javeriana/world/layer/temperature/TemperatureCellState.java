package org.javeriana.world.layer.temperature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.automata.core.cell.LayerCellState;

/**
 * Temperature cell state concrete implementation
 */
public class TemperatureCellState implements LayerCellState {

    private static final Logger logger = LogManager.getLogger(TemperatureCell.class);

    private double temperature;


    public TemperatureCellState(double temperature) {
        logger.info("New temperature state: " + temperature);
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
