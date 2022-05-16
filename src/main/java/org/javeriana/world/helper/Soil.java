package org.javeriana.world.helper;

public enum Soil {
    SAND(0.17, 0.07, 0.11),
    LOAMY_SAND(0.19, 0.10, 0.12),
    SANDY_LOAM(0.28,0.16,0.15),
    LOAM(0.30, 0.17, 0.18),
    SILT_LOAM(0.36, 0.21, 0.19),
    SILT(0.36, 0.22, 0.20),
    SILT_CLAY_LOAM(0.37,0.24, 0.18),
    SILT_CLAY(0.42,0.29, 0.19),
    CLAY(0.40,0.24,0.20);



    private double waterContentAtFieldCapacity;
    private double waterContentAtWiltingPoint;
    private double fieldCapacityMinusWiltingPoint;

    private Soil(double waterContentAtFieldCapacity, double waterContentAtWiltingPoint, double fieldCapacityMinusWiltingPoint) {
        this.waterContentAtFieldCapacity = waterContentAtFieldCapacity;
        this.waterContentAtWiltingPoint = waterContentAtWiltingPoint;
        this.fieldCapacityMinusWiltingPoint = fieldCapacityMinusWiltingPoint;
    }

    public double getWaterContentAtFieldCapacity() {
        return waterContentAtFieldCapacity;
    }

    public void setWaterContentAtFieldCapacity(double waterContentAtFieldCapacity) {
        this.waterContentAtFieldCapacity = waterContentAtFieldCapacity;
    }

    public double getWaterContentAtWiltingPoint() {
        return waterContentAtWiltingPoint;
    }

    public void setWaterContentAtWiltingPoint(double waterContentAtWiltingPoint) {
        this.waterContentAtWiltingPoint = waterContentAtWiltingPoint;
    }

    public double getFieldCapacityMinusWiltingPoint() {
        return fieldCapacityMinusWiltingPoint;
    }

    public void setFieldCapacityMinusWiltingPoint(double fieldCapacityMinusWiltingPoint) {
        this.fieldCapacityMinusWiltingPoint = fieldCapacityMinusWiltingPoint;
    }
}