package org.javeriana.world.layer.disease;

import org.javeriana.automata.core.cell.LayerCellState;

public class DiseaseCellState implements LayerCellState {

    private double probabilityDisease;
    private double percentageOfInsecticide;

    private boolean infected;

    public DiseaseCellState() {
    }

    public double getCurrentProbabilityDisease() {
        return probabilityDisease;
    }

    public void setCurrentProbabilityDisease(double currentProbabilityDisease) {
        this.probabilityDisease = currentProbabilityDisease;
    }

    public double getPercentageOfInsecticide() {
        return percentageOfInsecticide;
    }

    public void setPercentageOfInsecticide(double percentageOfInsecticide) {
        this.percentageOfInsecticide = percentageOfInsecticide;
    }

    public double getProbabilityDisease() {
        return probabilityDisease;
    }

    public void setProbabilityDisease(double probabilityDisease) {
        this.probabilityDisease = probabilityDisease;
    }

    public boolean isInfected() {
        return infected;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }
}
