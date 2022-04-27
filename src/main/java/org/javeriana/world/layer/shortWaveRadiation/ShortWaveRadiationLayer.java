package org.javeriana.world.layer.shortWaveRadiation;

import org.javeriana.automata.core.layer.GenericUniqueCellLayer;
import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.util.WorldConfiguration;
import org.javeriana.world.helper.DateHelper;
import org.javeriana.world.helper.ExtraterrestrialRadiation;
import org.javeriana.world.helper.Hemisphere;
import org.javeriana.world.helper.MonthlyDataLoader;
import org.javeriana.world.layer.LayerFunctionParams;
import org.javeriana.world.layer.data.MonthData;

import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

public class ShortWaveRadiationLayer extends GenericUniqueCellLayer<ShortWaveRadiationCell> {

    private double a_s = 0.25;
    private double b_s = 0.5;
    private final double albedoReflection = 0.23;

    private Hemisphere hemisphere;
    private double [] monthlyExtraterrestrialRadiationForLocation;

    private int latitudeDegrees;
    private Random random;

    private List<MonthData> monthlyData;

    private WorldConfiguration worldConfig =  WorldConfiguration.getPropsInstance();

    public ShortWaveRadiationLayer(Hemisphere hemisphere, int latitudeDegrees) {
        this.hemisphere = hemisphere;
        this.latitudeDegrees = latitudeDegrees;
        this.cell = new ShortWaveRadiationCell("radCell");
        this.random = new Random();
        this.setupLayer();
    }

    @Override
    public void setupLayer() {
        try{
            this.monthlyData = MonthlyDataLoader.loadMonthlyDataFile(this.worldConfig.getProperty("data.radiation"));
            if(this.hemisphere == Hemisphere.NORTHERN) {
                this.monthlyExtraterrestrialRadiationForLocation = ExtraterrestrialRadiation.northernData.get(
                        this.latitudeDegrees % 2 == 0 ? this.latitudeDegrees : this.latitudeDegrees + 1
                );
            } else {
                this.monthlyExtraterrestrialRadiationForLocation = ExtraterrestrialRadiation.southernData.get(
                        this.latitudeDegrees % 2 == 0 ? this.latitudeDegrees : this.latitudeDegrees + 1
                );
            }
        } catch(IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void executeLayer() {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public <P extends LayerExecutionParams> void executeLayer(P params) {
        LayerFunctionParams params1 = (LayerFunctionParams) params;
        String dateFormat = this.worldConfig.getProperty("date.format");
        try {
            int monthFromDate = DateHelper.getMonthFromStringDate(params1.getDate(), dateFormat);
            this.cell.setCellState(params1.getDate(),
                    new ShortWaveRadiationCellState(this.calculateNetShortWaveRadiationForMonth(monthFromDate))
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private double calculateNetShortWaveRadiationForMonth(int month) {
        return (1-this.albedoReflection) * this.calculateShortWaveRadiation(month);
    }

    private double calculateShortWaveRadiation(int month) {
        MonthData monthData = this.monthlyData.get(month);
        return (this.a_s + this.b_s * (this.getRandomFromMonthData(month)/monthData.getMaxValue())) * this.monthlyExtraterrestrialRadiationForLocation[month];
    }

    private double getRandomFromMonthData(int month) {
        MonthData monthData = this.monthlyData.get(month);
        return monthData.getAverage()-monthData.getStandardDeviation() + (((monthData.getAverage()+monthData.getStandardDeviation()) - (monthData.getAverage()-monthData.getStandardDeviation())) * this.random.nextDouble());
    }

}
