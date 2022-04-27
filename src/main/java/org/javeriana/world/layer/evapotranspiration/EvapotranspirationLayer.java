package org.javeriana.world.layer.evapotranspiration;

import org.javeriana.automata.core.layer.GenericUniqueCellLayer;
import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.world.layer.LayerFunctionParams;

import java.util.Random;

public class EvapotranspirationLayer extends GenericUniqueCellLayer<EvapotranspirationCell> {

    private double average;
    private double standardDeviation;

    private Random random;

    private String id;

    public EvapotranspirationLayer(double average, double standardDeviation) {
        this.average = average;
        this.standardDeviation = standardDeviation;
        this.setupLayer();
    }

    @Override
    public void setupLayer() {
        this.id = "evapoLayer";
        this.cell = new EvapotranspirationCell("evapoCell");
        this.random = new Random();
    }

    @Override
    public void executeLayer() {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public <P extends LayerExecutionParams> void executeLayer(P params) {
        LayerFunctionParams paramsCasted = (LayerFunctionParams) params;
        this.cell.setCellState(paramsCasted.getDate(),new EvapotranspirationCellState(this.generateEvapotranspiration()));
    }

    private double generateEvapotranspiration() {
        double rand = this.average-this.standardDeviation + (((this.average+this.standardDeviation) - (this.average-this.standardDeviation)) * this.random.nextDouble());
        return rand;
    }
}
