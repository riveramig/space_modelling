package org.javeriana.world.layer.crop.cell;

import org.javeriana.automata.core.cell.CellState;

public abstract class CropCellState implements CellState {
    protected double evapotranspiration;
    protected double growingDegreeDays;
    protected boolean isActiveCrop;
    protected double diseaseProbability;
    protected double aboveGroundBiomass;

    public CropCellState(double riceEvapotranspiration, double currentGrowingDegreeDays, boolean isActiveCrop, double currentDiseaseProbability, double currentAboveGroundBiomass) {
        this.evapotranspiration = riceEvapotranspiration;
        this.growingDegreeDays = currentGrowingDegreeDays;
        this.isActiveCrop = isActiveCrop;
        this.diseaseProbability = currentDiseaseProbability;
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

    public boolean isActiveCrop() {
        return isActiveCrop;
    }

    public void setActiveCrop(boolean activeCrop) {
        isActiveCrop = activeCrop;
    }

    public double getDiseaseProbability() {
        return diseaseProbability;
    }

    public void setDiseaseProbability(double diseaseProbability) {
        this.diseaseProbability = diseaseProbability;
    }

    public double getAboveGroundBiomass() {
        return aboveGroundBiomass;
    }

    public void setAboveGroundBiomass(double aboveGroundBiomass) {
        this.aboveGroundBiomass = aboveGroundBiomass;
    }
}
