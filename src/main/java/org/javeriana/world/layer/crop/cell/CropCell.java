package org.javeriana.world.layer.crop.cell;

import org.javeriana.automata.core.cell.CellState;
import org.javeriana.automata.core.cell.GenericWorldCell;

public abstract class CropCell<S extends CellState> extends GenericWorldCell<S> {
    protected double cropFactor_ini;
    protected double cropFactor_mid;
    protected double cropFactor_end;
    protected double degreeDays_mid;
    protected double degreeDays_end;
    protected int widthCrop;
    protected int longCrop;

    public CropCell(double cropFactor_ini, double cropFactor_mid, double cropFactor_end, double degreeDays_mid, double degreeDays_end, int widthCrop, int longCrop) {
        this.cropFactor_ini = cropFactor_ini;
        this.cropFactor_mid = cropFactor_mid;
        this.cropFactor_end = cropFactor_end;
        this.degreeDays_mid = degreeDays_mid;
        this.degreeDays_end = degreeDays_end;
        this.widthCrop = widthCrop;
        this.longCrop = longCrop;
    }

    public CropCell() {
    }

    public double getCropFactor_ini() {
        return cropFactor_ini;
    }

    public void setCropFactor_ini(double cropFactor_ini) {
        this.cropFactor_ini = cropFactor_ini;
    }

    public double getCropFactor_mid() {
        return cropFactor_mid;
    }

    public void setCropFactor_mid(double cropFactor_mid) {
        this.cropFactor_mid = cropFactor_mid;
    }

    public double getCropFactor_end() {
        return cropFactor_end;
    }

    public void setCropFactor_end(double cropFactor_end) {
        this.cropFactor_end = cropFactor_end;
    }

    public int getWidthCrop() {
        return widthCrop;
    }

    public void setWidthCrop(int widthCrop) {
        this.widthCrop = widthCrop;
    }

    public int getLongCrop() {
        return longCrop;
    }

    public void setLongCrop(int longCrop) {
        this.longCrop = longCrop;
    }

    public double getDegreeDays_mid() {
        return degreeDays_mid;
    }

    public void setDegreeDays_mid(double degreeDays_mid) {
        this.degreeDays_mid = degreeDays_mid;
    }

    public double getDegreeDays_end() {
        return degreeDays_end;
    }

    public void setDegreeDays_end(double degreeDays_end) {
        this.degreeDays_end = degreeDays_end;
    }
}