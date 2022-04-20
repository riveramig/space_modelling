package org.javeriana.environmentReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Temperature extends WeatherObject{

    private static final Logger logger = LogManager.getLogger(Temperature.class);

    private HashMap<Double,Double> saturationVapourPressureData;
    private double[] meanSaturationVapourPressure;

    private double meanSaturationVapourPressureMaxTemp;

    private double meanSaturationVapourPressureMinTemp;

    private double saturationPressure;

    private double[] slopeOfSaturationVapourPressureCurve;

    public Temperature(String temperatureFileName) {
        this.saturationVapourPressureData = this.loadSaturationVapourPressureData();
        this.loadAveragesMonthlyFile(temperatureFileName);
        this.meanSaturationVapourPressure = this.calculateSaturationVapourPressure();
        this.meanSaturationVapourPressureMaxTemp = this.saturationVapourPressureData.get(roundToHalf(this.maxFromMonthlyAverages));
        this.meanSaturationVapourPressureMinTemp = this.saturationVapourPressureData.get(roundToHalf(this.minFromMonthlyAverages));
        this.saturationPressure = (this.meanSaturationVapourPressureMaxTemp + this.meanSaturationVapourPressureMinTemp)/2;
        logger.info("Saturation pressure value: "+this.saturationPressure);
        logger.info("Mean saturation vapour pressure: "+Arrays.toString(this.meanSaturationVapourPressure));
        this.slopeOfSaturationVapourPressureCurve = this.calculateSlopeOfSaturationVapourPressureCurve();
        logger.info("Slope of saturation vapour pressure curve: "+Arrays.toString(this.slopeOfSaturationVapourPressureCurve));
    }

    private double[] calculateSlopeOfSaturationVapourPressureCurve() {
        return IntStream.range(0,12).mapToDouble((monthIndex)->
                (4098*(this.meanSaturationVapourPressure[monthIndex]))/(Math.pow(this.monthlyAverages[monthIndex]+237.3,2))).toArray();
    }

    private double[] calculateSaturationVapourPressure() {
        return Arrays.stream(this.monthlyAverages).map((current) ->
                (this.saturationVapourPressureData.get(roundToHalf(current)))
        ).toArray();
    }

    /**
     * Information loaded from https://www.fao.org/3/x0490e/x0490e0j.htm#annex%202.%20meteorological%20tables
     * @return map with all the information
     */
    private HashMap<Double,Double> loadSaturationVapourPressureData() {
        return new HashMap<Double,Double>(){{
            put(1.00,0.66);
            put(1.50,0.68);
            put(2.00,0.71);
            put(2.50,0.73);
            put(3.00,0.76);
            put(3.50,0.79);
            put(4.00,0.81);
            put(4.50,0.84);
            put(5.00,0.87);
            put(5.50,0.90);
            put(6.00,0.94);
            put(6.50,0.97);
            put(7.00,1.00);
            put(7.50,1.04);
            put(8.00,1.07);
            put(8.50,1.11);
            put(9.00,1.15);
            put(9.50,1.19);
            put(10.00,1.23);
            put(10.50,1.27);
            put(11.00,1.31);
            put(11.50,1.36);
            put(12.00,1.40);
            put(12.50,1.45);
            put(13.00,1.50);
            put(13.50,1.55);
            put(14.00,1.60);
            put(14.50,1.65);
            put(15.00,1.71);
            put(15.50,1.76);
            put(16.00,1.82);
            put(16.50,1.88);
            put(17.00,1.94);
            put(17.50,2.00);
            put(18.00,2.06);
            put(18.50,2.13);
            put(19.00,2.20);
            put(19.50,2.27);
            put(20.00,2.34);
            put(20.50,2.41);
            put(21.00,2.49);
            put(21.50,2.56);
            put(22.00,2.64);
            put(22.50,2.73);
            put(23.00,2.81);
            put(23.50,2.90);
            put(24.00,2.98);
            put(24.50,3.08);
            put(25.00,3.17);
            put(25.50,3.26);
            put(26.00,3.36);
            put(26.50,3.46);
            put(27.00,3.57);
            put(27.50,3.67);
            put(28.00,3.78);
            put(28.50,3.89);
            put(29.00,4.01);
            put(29.50,4.12);
            put(30.00,4.24);
            put(30.50,4.37);
            put(31.00,4.49);
            put(31.50,4.62);
            put(32.00,4.76);
            put(32.50,4.89);
            put(33.00,5.03);
            put(33.50,5.17);
            put(34.00,5.32);
            put(34.50,5.47);
            put(35.00,5.62);
            put(35.50,5.78);
            put(36.00,5.94);
            put(36.50,6.11);
            put(37.00,6.28);
            put(37.50,6.45);
            put(38.00,6.63);
            put(38.50,6.81);
            put(39.00,6.99);
            put(39.50,7.18);
            put(40.00,7.38);
            put(40.50,7.57);
            put(41.00,7.78);
            put(41.50,7.99);
            put(42.00,8.20);
            put(42.50,8.42);
            put(43.00,8.64);
            put(43.50,8.87);
            put(44.00,9.10);
            put(44.50,9.34);
            put(45.00,9.58);
            put(45.50,9.83);
            put(46.00,10.09);
            put(46.50,10.35);
            put(47.00,10.61);
            put(47.50,10.89);
            put(48.00,11.16);
            put(48.50,11.45);
        }};
    }

    private static double roundToHalf(double d) {
        return Math.round(d * 2) / 2.0;
    }

    public double[] getSlopeOfSaturationVapourPressureCurve() {
        return slopeOfSaturationVapourPressureCurve;
    }

    public double[] getMonthlyAverageTemperatures() {
        return this.monthlyAverages;
    }

    public double[] getMeanSaturationVapourPressure() {
        return meanSaturationVapourPressure;
    }

    public double getMeanSaturationVapourPressureMaxTemp() {
        return meanSaturationVapourPressureMaxTemp;
    }

    public double getMeanSaturationVapourPressureMinTemp() {
        return meanSaturationVapourPressureMinTemp;
    }

    public double getSaturationPressure() {
        return saturationPressure;
    }

    @Override
    protected double calculateProbability(double max, double exceedance) {
        return 0;
    }
}
