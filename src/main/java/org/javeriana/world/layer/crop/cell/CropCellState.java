package org.javeriana.world.layer.crop.cell;

import org.javeriana.automata.core.cell.LayerCellState;

public class CropCellState implements LayerCellState {
    protected double evapotranspiration;
    protected double growingDegreeDays;
    protected double aboveGroundBiomass;

    protected double cumulatedEvapotranspiration;

    protected double depletionFractionAdjusted;

    protected double rootZoneDepletionAtTheEndOfDay;



    public CropCellState(double riceEvapotranspiration, double currentGrowingDegreeDays, double currentAboveGroundBiomass, double cumulatedEvapotranspiration, double depletionFractionAdjusted, double rootZoneDepletionAtTheEndOfDay) {
        this.evapotranspiration = riceEvapotranspiration;
        this.growingDegreeDays = currentGrowingDegreeDays;
        this.aboveGroundBiomass = currentAboveGroundBiomass;
        this.cumulatedEvapotranspiration = cumulatedEvapotranspiration;
        this.depletionFractionAdjusted = depletionFractionAdjusted;
        this.rootZoneDepletionAtTheEndOfDay = rootZoneDepletionAtTheEndOfDay;
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

    public double getDepletionFractionAdjusted() {
        return depletionFractionAdjusted;
    }
    public void setDepletionFractionAdjusted(double depletionFractionAdjusted) {
        this.depletionFractionAdjusted = depletionFractionAdjusted;
    }

    public double getRootZoneDepletionAtTheEndOfDay() {
        return rootZoneDepletionAtTheEndOfDay;
    }

    public void setRootZoneDepletionAtTheEndOfDay(double rootZoneDepletionAtTheEndOfDay) {
        this.rootZoneDepletionAtTheEndOfDay = rootZoneDepletionAtTheEndOfDay;
    }
}
