package org.javeriana.world.layer.crop.cell;

import org.javeriana.automata.core.cell.LayerCellState;

public class CropCellState implements LayerCellState {
    protected double evapotranspiration;
    protected double growingDegreeDays;
    protected double aboveGroundBiomass;

    protected double cumulatedEvapotranspiration;

    public CropCellState(double riceEvapotranspiration, double currentGrowingDegreeDays, double currentAboveGroundBiomass, double cumulatedEvapotranspiration) {
        this.evapotranspiration = riceEvapotranspiration;
        this.growingDegreeDays = currentGrowingDegreeDays;
        this.aboveGroundBiomass = currentAboveGroundBiomass;
        this.cumulatedEvapotranspiration = cumulatedEvapotranspiration;
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

    public double getCumulatedEvapotranspiration() {
        return cumulatedEvapotranspiration;
    }

    public void setCumulatedEvapotranspiration(double cumulatedEvapotranspiration) {
        this.cumulatedEvapotranspiration = cumulatedEvapotranspiration;
    }
}
