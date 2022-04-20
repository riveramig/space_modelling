package org.javeriana.environmentReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RelativeHumidity extends WeatherObject{


    private double[] actualVapourPressure;

    private double actualVapourPressureValue;
    private Temperature temperature;

    private static final Logger logger = LogManager.getLogger(RelativeHumidity.class);

    public RelativeHumidity(String relativeHumidityFileName, Temperature temperature) {
        this.temperature = temperature;
        this.loadAveragesMonthlyFile(relativeHumidityFileName);
        this.actualVapourPressure=this.calculateActualVapourPressure();
        this.actualVapourPressureValue = ((this.temperature.getMeanSaturationVapourPressureMinTemp()*(this.maxFromMonthlyAverages/100))+(this.temperature.getMeanSaturationVapourPressureMaxTemp()*(this.minFromMonthlyAverages/100)))/2;
        logger.info("Actual vapour pressure: "+Arrays.toString(this.actualVapourPressure));
        logger.info("Actual vapour pressure value: "+this.actualVapourPressureValue);
    }

    /**
     * Calculate saturation vapour pressure from FAO
     * @return
     */


    /**
     * Calculate Actual vapour pressure from FAO
     * @return
     */
    private double[] calculateActualVapourPressure() {
        return IntStream.range(0,12).mapToDouble((monthIndex)->
                (this.monthlyAverages[monthIndex]/100)*this.temperature.getMeanSaturationVapourPressure()[monthIndex]).toArray();
    }


    public double[] getActualVapourPressure() {
        return actualVapourPressure;
    }

    public double getActualVapourPressureValue() {
        return actualVapourPressureValue;
    }

    @Override
    protected double calculateProbability(double max, double exceedance) {
        return 0;
    }
}
