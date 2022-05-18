package org.javeriana.agents.peasant;

import BESA.Kernel.Agent.StateBESA;

import java.util.HashSet;
import java.util.Set;

public class PeasantState extends StateBESA {
    private String cropId;
    private double probabilityOfDailyCropSupervision;

    private double probabilityOfWaterCropIfWaterStress;

    private double probabilityOfPesticideIfDisease;
    private double probabilityToTakeMonthlyCourse;

    private Set<Integer> monthsTakenCourse = new HashSet<>();


    public PeasantState(String cropId, double probabilityOfDailyCropSupervision, double probabilityToTakeMonthlyCourse, double probabilityOfWaterCropIfWaterStress, double probabilityOfPesticideIfDisease) {
        this.cropId = cropId;
        this.probabilityOfDailyCropSupervision = probabilityOfDailyCropSupervision;
        this.probabilityToTakeMonthlyCourse = probabilityToTakeMonthlyCourse;
        this.probabilityOfWaterCropIfWaterStress = probabilityOfWaterCropIfWaterStress;
        this.probabilityOfPesticideIfDisease = probabilityOfPesticideIfDisease;
    }

    public String getCropId() {
        return cropId;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
    }

    public double getProbabilityOfDailyCropSupervision() {
        return probabilityOfDailyCropSupervision;
    }

    public void setProbabilityOfDailyCropSupervision(double probabilityOfDailyCropSupervision) {
        this.probabilityOfDailyCropSupervision = probabilityOfDailyCropSupervision;
    }

    public double getProbabilityToTakeMonthlyCourse() {
        return probabilityToTakeMonthlyCourse;
    }

    public void setProbabilityToTakeMonthlyCourse(double probabilityToTakeMonthlyCourse) {
        this.probabilityToTakeMonthlyCourse = probabilityToTakeMonthlyCourse;
    }

    public Set<Integer> getMonthsTakenCourse() {
        return monthsTakenCourse;
    }

    public void addMonth(int month) {
        this.monthsTakenCourse.add(month);
    }

    public double getProbabilityOfWaterCropIfWaterStress() {
        return probabilityOfWaterCropIfWaterStress;
    }

    public void setProbabilityOfWaterCropIfWaterStress(double probabilityOfWaterCropIfWaterStress) {
        this.probabilityOfWaterCropIfWaterStress = probabilityOfWaterCropIfWaterStress;
    }

    public void setMonthsTakenCourse(Set<Integer> monthsTakenCourse) {
        this.monthsTakenCourse = monthsTakenCourse;
    }

    public double getProbabilityOfPesticideIfDisease() {
        return probabilityOfPesticideIfDisease;
    }

    public void setProbabilityOfPesticideIfDisease(double probabilityOfPesticideIfDisease) {
        this.probabilityOfPesticideIfDisease = probabilityOfPesticideIfDisease;
    }
}
