package org.javeriana.environmentReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.IntStream;

public class EvapotranspirationReference extends WeatherObject{

    private static final Logger logger = LogManager.getLogger(EvapotranspirationReference.class);

    private double[] evapotranspirationReferenceMonthlyPeriods;

    private Temperature temperature;
    private Radiation radiation;
    private RelativeHumidity relativeHumidity;
    private SoilHeatFlux soilHeatFlux;
    private Wind wind;
    private Atmospheric atmospheric;

    public EvapotranspirationReference(
            Temperature temperature,
            Radiation radiation,
            RelativeHumidity relativeHumidity,
            SoilHeatFlux soilHeatFlux,
            Wind wind,
            Atmospheric atmospheric
    ) {
        this.temperature = temperature;
        this.radiation = radiation;
        this.relativeHumidity = relativeHumidity;
        this.soilHeatFlux = soilHeatFlux;
        this.wind = wind;
        this.atmospheric = atmospheric;
        this.evapotranspirationReferenceMonthlyPeriods = this.calculateEvapotranspirationReferenceForMonthlyPeriods();
        logger.info("Evapotranspiration reference from climatic data: "+ Arrays.toString(this.evapotranspirationReferenceMonthlyPeriods));
    }

    private double[] calculateEvapotranspirationReferenceForMonthlyPeriods() {
        double[] slopeVapourPressureCurve = this.temperature.getSlopeOfSaturationVapourPressureCurve();
        double[] netRadiation = this.radiation.getNetRadiation();
        double[] soilHeatFluxDensity = this.soilHeatFlux.getSoilHeatFluxMonthlyAverages();
        double psycrhometricConstant = this.atmospheric.getPsychrometricConstant();
        double windSpeedAverage = this.wind.getWindSpeed();
        double saturationVapourPressure = this.temperature.getSaturationPressure();
        double actualVapourPressure = this.relativeHumidity.getActualVapourPressureValue();
        double[] temperatureMonthAverage = this.temperature.getMonthlyAverageTemperatures();

        return IntStream.range(0,12).mapToDouble((monthIndex)->
                        (((0.408*slopeVapourPressureCurve[monthIndex]*(netRadiation[monthIndex]-soilHeatFluxDensity[monthIndex]))+(psycrhometricConstant*(900/(temperatureMonthAverage[monthIndex]+273))*windSpeedAverage*(saturationVapourPressure-actualVapourPressure)))
                                /
                                (slopeVapourPressureCurve[monthIndex]+(psycrhometricConstant*(1+0.34*windSpeedAverage)))
                        )
                ).toArray();
    }

    @Override
    protected double calculateProbability(double max, double exceedance) {
        return 0;
    }
}
