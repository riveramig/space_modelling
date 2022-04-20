package org.javeriana.util.cropData;

public class CropCoefficient {

    private String cropType;
    private double kIni;
    private double kMid;
    private double kEnd;

    private double gddMid;
    private double gddEnd;

    public CropCoefficient() {
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public double getkIni() {
        return kIni;
    }

    public void setkIni(double kIni) {
        this.kIni = kIni;
    }

    public double getkMid() {
        return kMid;
    }

    public void setkMid(double kMid) {
        this.kMid = kMid;
    }

    public double getkEnd() {
        return kEnd;
    }

    public void setkEnd(double kEnd) {
        this.kEnd = kEnd;
    }

    public double getGddMid() {
        return gddMid;
    }

    public void setGddMid(double gddMid) {
        this.gddMid = gddMid;
    }

    public double getGddEnd() {
        return gddEnd;
    }

    public void setGddEnd(double gddEnd) {
        this.gddEnd = gddEnd;
    }
}
