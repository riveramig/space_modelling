package org.javeriana.world.layer.crop.cell;

import org.javeriana.automata.core.cell.CellState;

public abstract class CropCellState implements CellState {
    protected double riceEvapotranspiration;
    protected double currentGrowingDegreeDays;
    protected boolean isActiveCrop;
    protected double currentDiseaseProbability;
    protected double currentAboveGroundBiomass;

    public CropCellState(double riceEvapotranspiration, double currentGrowingDegreeDays, boolean isActiveCrop, double currentDiseaseProbability, double currentAboveGroundBiomass) {
        this.riceEvapotranspiration = riceEvapotranspiration;
        this.currentGrowingDegreeDays = currentGrowingDegreeDays;
        this.isActiveCrop = isActiveCrop;
        this.currentDiseaseProbability = currentDiseaseProbability;
        this.currentAboveGroundBiomass = currentAboveGroundBiomass;
    }

    public CropCellState() {
    }

    public double getRiceEvapotranspiration() {
        return riceEvapotranspiration;
    }

    public void setRiceEvapotranspiration(double riceEvapotranspiration) {
        this.riceEvapotranspiration = riceEvapotranspiration;
    }

    public double getCurrentGrowingDegreeDays() {
        return currentGrowingDegreeDays;
    }

    public void setCurrentGrowingDegreeDays(double currentGrowingDegreeDays) {
        this.currentGrowingDegreeDays = currentGrowingDegreeDays;
    }

    public boolean isActiveCrop() {
        return isActiveCrop;
    }

    public void setActiveCrop(boolean activeCrop) {
        isActiveCrop = activeCrop;
    }

    public double getCurrentDiseaseProbability() {
        return currentDiseaseProbability;
    }

    public void setCurrentDiseaseProbability(double currentDiseaseProbability) {
        this.currentDiseaseProbability = currentDiseaseProbability;
    }

    public double getCurrentAboveGroundBiomass() {
        return currentAboveGroundBiomass;
    }

    public void setCurrentAboveGroundBiomass(double currentAboveGroundBiomass) {
        this.currentAboveGroundBiomass = currentAboveGroundBiomass;
    }
}
