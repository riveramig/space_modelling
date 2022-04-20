package org.javeriana.environmentReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SoilHeatFlux extends WeatherObject{

    private static final Logger logger = LogManager.getLogger(SoilHeatFlux.class);
    private Temperature temperature;
    private double[] soilHeatFluxMonthlyAverages;

    public SoilHeatFlux(Temperature temperature) {
        this.temperature=temperature;
        this.soilHeatFluxMonthlyAverages = this.calculateMonthlyPeriods();
        logger.info("Soil heat flux from temperature averages: "+ Arrays.toString(this.soilHeatFluxMonthlyAverages));
    }

    private double[] calculateMonthlyPeriods(){
        return IntStream.range(0,12).mapToDouble((index)-> {
            if(index==0){
                return 0;
            } else {
                return 0.14*(temperature.getMonthlyAverageTemperatures()[index]-temperature.getMonthlyAverageTemperatures()[index-1]);
            }
        }).toArray();
    }

    private double calculateDayAndTenDaysPeriods(){
        return 0;
    }

    private double calculateHourlyAndShorterPeriods(double netRadiation){
        return 0.1*netRadiation;
    }

    public double[] getSoilHeatFluxMonthlyAverages() {
        return soilHeatFluxMonthlyAverages;
    }

    @Override
    protected double calculateProbability(double max, double exceedance) {
        return 0;
    }
}
