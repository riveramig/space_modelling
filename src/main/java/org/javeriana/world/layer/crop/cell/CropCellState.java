package org.javeriana.world.layer.crop.cell;

import org.javeriana.automata.core.cell.LayerCellState;

public class CropCellState implements LayerCellState {
    protected double evapotranspiration;
    protected double growingDegreeDays;
    protected double aboveGroundBiomass;

    public CropCellState(double riceEvapotranspiration, double currentGrowingDegreeDays, double currentAboveGroundBiomass) {
        this.evapotranspiration = riceEvapotranspiration;
        this.growingDegreeDays = currentGrowingDegreeDays;
        this.aboveGroundBiomass = currentAboveGroundBiomass;
    }

    public CropCellState() {
    }

    public double getEvapotranspiration() {
        return evapotranspiration;
    }

    public void setEvapotranspiration(double evapotranspiration) {
        this.evapotranspiration = evapotranspiration;
    }

    public double getGrowingDegreeDays() {
        return growingDegreeDays;
    }

    public void setGrowingDegreeDays(double growingDegreeDays) {
        this.growingDegreeDays = growingDegreeDays;
    }

    public double getAboveGroundBiomass() {
        return aboveGroundBiomass;
    }

    public void setAboveGroundBiomass(double aboveGroundBiomass) {
        this.aboveGroundBiomass = aboveGroundBiomass;
    }
}
