package org.javeriana.world.farm;

import org.javeriana.environmentAdjusted.GrowingDegreeDays;

import java.util.List;

public class Crop {
    private String cropType;
    private int widthField;
    private int longField;
    private GrowingDegreeDays growingDegreeDays;
    private double currentEvapotranspiration;
    private List<Double> historicEvapotranspiration;
    private double perturbation;
    private double disease;
    private boolean isCropActive;

    public Crop(String cropType, int widthField, int longField) {
        this.cropType = cropType;
        this.widthField = widthField;
        this.longField = longField;

    }
}
