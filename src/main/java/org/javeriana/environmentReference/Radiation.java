package org.javeriana.environmentReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Radiation extends WeatherObject{

    private static final Logger logger = LogManager.getLogger(Radiation.class);

    private double a_s = 0.25;
    private double b_s = 0.5;
    private double kelvin = 273.16;
    private double albedoReflection = 0.23;
    private Temperature temperature;
    private RelativeHumidity relativeHumidity;

    public enum Hemisphere {
        SOUTHERN,
        NORTHERN
    }

    private HashMap<Integer, double[]> extraterrestrialRadiation;
    private HashMap<Double,Double> stefanBoltzmannTemperature;
    private double[] extraterrestrialRadiationForLocation;

    private double[] shortWaveRadiation;
    private double[] clearSkyRadiation;
    private double[] netLongWaveRadiation;
    private double[] netShortWaveRadiation;
    private double[] netRadiation;

    public Radiation(Hemisphere hemisphere, int latitudeDegrees, String filename, Temperature temperature, RelativeHumidity relativeHumidity) {
        this.temperature = temperature;
        this.relativeHumidity = relativeHumidity;
        this.loadAveragesMonthlyFile(filename);
        this.stefanBoltzmannTemperature = this.stefanBoltzmannDifferentTemperaturesData();
        if(hemisphere == Hemisphere.NORTHERN){
            this.extraterrestrialRadiation=this.loadNorthernData();
        }else{
            this.extraterrestrialRadiation=this.loadSouthernData();
        }
        this.extraterrestrialRadiationForLocation = this.extraterrestrialRadiation.get(latitudeDegrees % 2 == 0 ? latitudeDegrees : latitudeDegrees + 1);
        logger.info("Extraterrestrial loaded: "+ Arrays.toString(this.extraterrestrialRadiationForLocation));
        this.shortWaveRadiation = this.calculateShortWaveRadiation();
        logger.info("Short wave radiation: "+Arrays.toString(this.shortWaveRadiation));
        this.netShortWaveRadiation = this.calculateNetShortWaveRadiation();
        logger.info("Net short wave radiation: "+Arrays.toString(this.netShortWaveRadiation));
        this.clearSkyRadiation = this.calculateClearSkyRadiation();
        logger.info("Clear sky radiation: "+Arrays.toString(this.clearSkyRadiation));
        this.netLongWaveRadiation = this.calculateNetLongWaveRadiation();
        logger.info("Net long wave radiation: "+Arrays.toString(this.netLongWaveRadiation));
        this.netRadiation = this.calculateNetRadiation();
        logger.info("Net radiation: "+Arrays.toString(this.netRadiation));
    }

    /**
     * Data loaded from https://www.fao.org/3/x0490e/x0490e0j.htm#annex%202.%20meteorological%20tables
     * @return
     */
    private HashMap<Integer, double[]> loadSouthernData() {
        return new HashMap<Integer, double[]>(){{
            put (0, new double[]{36.2,37.5,37.9,36.8,34.8,33.4,33.9,35.7,37.3,37.4,36.3,35.6});
            put (2, new double[]{36.9,37.9,38.0,36.4,34.1,31.6,33.1,35.2,37.1,37.7,37.0,36.4});
            put (4, new double[]{37.6,38.3,38.0,36.0,33.4,31.8,32.3,34.6,37.0,38.0,37.6,37.2});
            put (6, new double[]{38.3,38.7,38.0,35.6,32.7,30.9,31.5,34.0,36.8,38.2,38.2,38.0});
            put (8, new double[]{38.9,39.0,37.9,35.1,31.9,30.0,30.7,33.4,36.6,38.4,38.8,38.7});
            put(10, new double[]{39.5,39.3,37.8,34.6,31.1,29.1,29.8,32.8,36.3,38.5,39.3,39.4});
            put(12, new double[]{40.1,39.6,37.7,34.0,30.2,28.1,28.9,32.1,36.0,38.6,39.8,40.0});
            put(14, new double[]{40.6,39.7,37.5,33.4,29.4,27.2,27.9,31.3,35.6,38.7,40.2,40.6});
            put(16, new double[]{41.1,39.9,37.2,32.8,28.5,26.2,27.0,30.6,35.2,38.7,40.6,41.2});
            put(18, new double[]{41.5,40.0,37.0,32.1,27.5,25.1,26.0,29.8,34.7,38.7,40.9,41.7});
            put(20, new double[]{41.9,40.0,36.6,31.3,26.6,24.1,25.0,28.9,34.2,38.6,41.2,42.1});
            put(22, new double[]{42.2,40.1,36.2,30.6,25.6,23.0,24.0,28.1,33.7,38.4,41.4,42.6});
            put(24, new double[]{42.5,40.0,35.8,29.8,24.6,21.9,22.9,27.2,33.1,38.3,41.7,43.0});
            put(26, new double[]{42.8,39.9,35.3,29.0,23.5,20.8,21.8,26.3,32.5,38.0,41.8,43.3});
            put(28, new double[]{43.0,39.8,34.8,28.1,22.5,19.7,20.7,25.3,31.8,37.8,41.9,43.6});
            put(30, new double[]{43.1,39.6,34.3,27.2,21.4,18.5,19.6,24.3,31.1,37,5,42.0,43.9});
            put(32, new double[]{43.3,39.4,33.7,26.3,20.3,17.4,18.5,23.3,30.4,37.1,42.0,44.1});
            put(34, new double[]{43.4,39.2,33.0,25.3,19.2,16.2,17.4,22.3,29.6,36.7,42.0,44.3});
            put(36, new double[]{43.4,38.9,32.4,24.3,18.1,15.1,16.2,21.2,28.8,36.3,42.0,44.4});
            put(38, new double[]{43.4,38.5,31.7,23.3,16.9,13.9,15.1,20.2,28.0,35.8,41.9,44.5});
            put(40, new double[]{43.4,38.1,30.9,22.3,15.8,12.8,13.9,19.1,27.1,35.3,41.8,44.6});
            put(42, new double[]{43.3,37.7,30.1,21.2,14.6,11.6,12.8,18.0,26.2,34.7,41.6,44.6});
            put(44, new double[]{43.2,37.2,29.3,20.1,13.5,10.5,11.6,16.8,25.2,34.1,41.4,44.6});
            put(46, new double[]{43.0,36.7,28.4,19.0,12.3,9.3,10.4,15.7,24.3,33.5,41.1,44.6});
            put(48, new double[]{42.9,36.2,27.5,17.9,11.1,8.2,9.3,14.6,23.3,32.8,40.9,44.5});
            put(50, new double[]{42.7,35.6,26.6,16.7,10.0,7.1,8.2,13.4,22.2,32.1,40.6,44.5});
            put(52, new double[]{42.5,35.0,25.6,15.6,8.8,6.0,7.1,12.2,21.2,31.4,40.2,44.4});
            put(54, new double[]{42.2,34.3,24.6,14.4,7.7,4.9,6.0,11.1,20.1,30.6,39.9,44.3});
            put(56, new double[]{42.0,33.7,23.6,13.2,6.6,3.9,4.9,9.9,19.0,29.8,39.5,44.1});
            put(58, new double[]{41.7,33.0,22.6,12.0,5.5,2.9,3.9,8.7,17.9,28.9,39.1,44.0});
            put(60, new double[]{41.5,32.3,21.5,10.8,4.4,2.0,2.9,7.6,16.7,28.1,38.7,43.9});
            put(62, new double[]{41.2,31.5,20.4,9.6,3.4,1.2,2.0,6.4,15.5,27.2,38.3,43.9});
            put(64, new double[]{41.0,30.8,19.3,8.4,2.4,0.6,1.2,5.3,14.4,26.3,38.0,43.9});
            put(66, new double[]{40.9,30.0,18.1,7.2,1.5,0.1,0.5,4.2,13.1,25.4,37.6,44.1});
            put(68, new double[]{41.0,29.3,16.9,6.0,0.8,0.0,0.0,3.2,11.9,24.4,37.4,44.7});
            put(70, new double[]{41.4,28.6,15.8,4.9,0.2,0.0,0.0,2.2,10.7,23.5,37.3,45.3});
        }};
    }

    /**
     * Data loaded from https://www.fao.org/3/x0490e/x0490e0j.htm#annex%202.%20meteorological%20tables
     * @return
     */
    private HashMap<Integer, double[]> loadNorthernData() {
        return new HashMap<Integer, double[]>(){{
            put (0, new double[]{0.0,2.6,10.4,23.0,35.2,42.5,39.4,28.0,14.9,4.9,0.1,0.0});
            put (2, new double[]{0.1,3.7,11.7,23.9,35.3,42.0,38.9,28.6,16.1,6.0,0.7,0.0});
            put (4, new double[]{0.6,4.8,12.9,24.8,35.6,41.4,38.8,29.3,17.3,7.2,1.5,0.1});
            put (6, new double[]{1.4,5.9,14.1,25.8,35.9,41.2,38.8,30.0,18.4,8.5,2.4,0.6});
            put (8, new double[]{2.3,7.1,15.4,26.6,36.3,41.2,39.0,30.6,19.5,9.7,3.4,1.3});
            put (10, new double[]{3.3,8.3,16.6,27.5,36.6,41.2,39.2,31.3,20.6,10.9,4.4,2.2});
            put (12, new double[]{4.3,9.6,17.7,28.4,37.0,41.3,39.4,32.0,21.7,12.1,5.5,3.1});
            put (14, new double[]{5.4,10.8,18.9,29.2,37,4,41.4,39.6,32.6,22.7,13.3,6.7,4.2});
            put (16, new double[]{6.5,12.0,20.0,30.0,37.8,41.5,39.8,33.2,23.7,14.5,7.8,5.2});
            put (18, new double[]{7.7,13.2,21.1,30.8,38.2,41.6,40.1,33.8,24.7,15.7,9.0,6.4});
            put (20, new double[]{8.9,14.4,22.2,31.5,38.5,41.7,40.2,34.4,25.7,16.9,10.2,7.5});
            put (22, new double[]{10.1,15.7,23.3,32.2,38.8,41.8,40.4,34.9,26.6,18.1,11.4,8.7});
            put (24, new double[]{11.3,16.9,24.3,32.9,39.1,41.9,40.6,35.4,27.5,19.2,12.6,9.9});
            put (26, new double[]{12.5,18.0,25.3,33.5,39.3,41.9,40.7,35.9,28.4,20.3,13.9,11.1});
            put (28, new double[]{13.8,19.2,26.3,34.1,39.5,41.9,40.8,36.3,29.2,21.4,15.1,12.4});
            put (30, new double[]{15.0,20.4,27.2,34.7,39.7,41.9,40.8,36.7,30.0,22.5,16.3,13.6});
            put (32, new double[]{16.2,21.5,28.1,35.2,39.9,41.8,40.8,37.0,30.7,23.6,17.5,14.8});
            put (34, new double[]{17.5,22.6,29.0,35.7,40.0,41.7,40.8,37.4,31.5,24.6,18.7,16.1});
            put (36, new double[]{18.7,23.7,29.9,36.1,40.0,41.6,40.8,37.6,32.1,25.6,19.9,17.3});
            put (38, new double[]{19.9,24.8,30.7,36.5,40.0,41.4,40.7,37.9,32.8,26.6,21.1,18.5});
            put (40, new double[]{21.1,25.8,31.4,36.8,40.0,41.2,40.6,38.0,33.4,27.6,22.2,19.8});
            put (42, new double[]{22.3,26.8,32.2,37.1,40.0,40.9,40.4,38.2,33.9,28.5,23.3,21.0});
            put (44, new double[]{23.4,27.8,32.8,37.4,39.9,40.6,40.2,38.3,34.5,29.3,24.5,22.2});
            put (46, new double[]{24.6,28.8,33.5,37.6,39.7,40.3,39.9,38.3,34.9,30.2,25.5,23.3});
            put (48, new double[]{25.7,29.7,34.1,37.8,39.5,40.0,39.6,38.4,35.4,31.0,26.6,24.5});
            put (50, new double[]{26.8,30.6,34.7,37.9,39.3,39.5,39.3,38.3,35.8,31.8,27.7,25.6});
            put (52, new double[]{27.9,31.5,35.2,38.0,39.0,39.1,38.9,38.2,36.1,32.5,28.7,26.8});
            put (54, new double[]{28.9,32.3,35.7,38.1,38.7,38.6,38.5,38.1,36.4,33.2,29.6,27.9});
            put (56, new double[]{29.9,33.1,36.1,38.1,38.4,38.1,38.1,38.0,36.7,33.9,30.6,28.9});
            put (58, new double[]{30.9,33.8,36.5,38.0,38.0,37.6,37.6,37.8,36.9,34.5,31.5,30.0});
            put (60, new double[]{31.9,34.5,36.9,37.9,37.6,37.0,37.1,37.5,37.1,35.1,32.4,31.0});
            put (62, new double[]{32.8,35.2,37.2,37.8,37.1,36.3,36.5,37.2,37,2,35.6,33.3,32.0});
            put (64, new double[]{33.7,35.8,37.4,37.6,36.6,35.7,35.9,36.9,37.3,36.1,34.1,32.9});
            put (66, new double[]{34.6,36.4,37.6,37.4,36.0,35.0,35.3,36.5,37.3,36.6,34.9,33.9});
            put (68, new double[]{35.4,37.0,37.8,37.1,35.4,34.2,34.6,36.1,37.3,37.0,35.6,34.8});
            put (70, new double[]{36.2,37.5,37.9,36.8,34.8,33,4,33.9,35.7,37.2,37.4,36.3,35.6});
        }};
    }

    /**
     * Calculate short wave radiation from FAO equations
     * @return
     */
    private double[] calculateShortWaveRadiation() {
        AtomicInteger index = new AtomicInteger(0);
        return Arrays.stream(this.monthlyAverages).map((current) ->
            (this.a_s + this.b_s * (current / this.maxFromMonthlyAverages)) * this.extraterrestrialRadiationForLocation[index.getAndIncrement()]
        ).toArray();
    }

    /**
     * Calculate clear sky radiation from FAO
     * @return
     */
    private double[] calculateClearSkyRadiation(){
        AtomicInteger index = new AtomicInteger(0);
        return Arrays.stream(this.extraterrestrialRadiationForLocation).map((current) ->
                (this.a_s+this.b_s)*current).toArray();
    }

    /**
     * Calculate net short wave radiation from FAO equations
     * @return
     */
    private double[] calculateNetShortWaveRadiation() {
        AtomicInteger index = new AtomicInteger(0);
        return Arrays.stream(this.shortWaveRadiation).map((current) ->
                (1 - this.albedoReflection) * current
        ).toArray();
    }

    /**
     * Calculate net long wave radiation from FAO equations
     * @return
     */
    private double[] calculateNetLongWaveRadiation() {
        return IntStream.range(0,12).mapToDouble((rangeIndex)->
                (this.stefanBoltzmannTemperature.get(roundToHalf(this.temperature.getMonthlyAverageTemperatures()[rangeIndex]))
                *(0.34-0.14*Math.sqrt(this.relativeHumidity.getActualVapourPressureValue()))*(1.35*(this.shortWaveRadiation[rangeIndex]/this.clearSkyRadiation[rangeIndex])-0.35))).toArray();
    }

    /**
     * Calculate net radiation from FAO equations
     * @return
     */
    private double[] calculateNetRadiation() {
        return IntStream.range(0,12).mapToDouble((rangeIndex)->
                (this.netShortWaveRadiation[rangeIndex]-this.netLongWaveRadiation[rangeIndex])).toArray();
    }

    private HashMap<Double,Double> stefanBoltzmannDifferentTemperaturesData() {
        return new HashMap<Double,Double>(){{
            put(1.00,27.70);
            put(1.50,27.90);
            put(2.00,28.11);
            put(2.50,28.31);
            put(3.00,28.52);
            put(3.50,28.72);
            put(4.00,28.93);
            put(4.50,29.14);
            put(5.00,29.35);
            put(5.50,29.56);
            put(6.00,29.78);
            put(6.50,29.99);
            put(7.00,30.21);
            put(7.50,30.42);
            put(8.00,30.64);
            put(8.50,30.86);
            put(9.00,31.08);
            put(9.50,31.30);
            put(10.00,31.52);
            put(10.50,31.74);
            put(11.00,31.97);
            put(11.50,32.19);
            put(12.00,32.42);
            put(12.50,32.65);
            put(13.00,32.88);
            put(13.50,33.11);
            put(14.00,33.34);
            put(14.50,33.57);
            put(15.00,33.81);
            put(15.50,34.04);
            put(16.00,34.28);
            put(16.50,34.52);
            put(17.00,34.75);
            put(17.50,34.99);
            put(18.00,35.24);
            put(18.50,35.48);
            put(19.00,35.72);
            put(19.50,35.97);
            put(20.00,36.21);
            put(20.50,36.46);
            put(21.00,36.71);
            put(21.50,36.96);
            put(22.00,37.21);
            put(22.50,37.47);
            put(23.00,37.72);
            put(23.50,37.98);
            put(24.00,38.23);
            put(24.50,38.49);
            put(25.00,38.75);
            put(25.50,39.01);
            put(26.00,39.27);
            put(26.50,39.53);
            put(27.00,39.80);
            put(27.50,40.06);
            put(28.00,40.33);
            put(28.50,40.60);
            put(29.00,40.87);
            put(29.50,41.14);
            put(30.00,41.41);
            put(30.50,41.69);
            put(31.00,41.96);
            put(31.50,42.24);
            put(32.00,42.52);
            put(32.50,42.80);
            put(33.00,43.08);
            put(33.50,43.36);
            put(34.00,43.64);
            put(34.50,43.93);
            put(35.00,44.21);
            put(35.50,44.50);
            put(36.00,44.79);
            put(36.50,45.08);
            put(37.00,45.37);
            put(37.50,45.67);
            put(38.00,45.96);
            put(38.50,46.26);
            put(39.00,46.56);
            put(39.50,46.85);
            put(40.00,47.15);
            put(40.50,47.46);
            put(41.00,47.76);
            put(41.50,48.06);
            put(42.00,48.37);
            put(42.50,48.68);
            put(43.00,48.99);
            put(43.50,49.30);
            put(44.00,49.61);
            put(44.50,49.92);
            put(45.00,50.24);
            put(45.50,50.56);
            put(46.00,50.87);
            put(46.50,51.19);
            put(47.00,51.51);
            put(47.50,51.84);
            put(48.00,52.16);
            put(48.50,52.49);
        }};
    }

    public double[] getNetLongWaveRadiation() {
        return netLongWaveRadiation;
    }

    public double[] getNetShortWaveRadiation() {
        return netShortWaveRadiation;
    }

    public double[] getNetRadiation() {
        return netRadiation;
    }

    private static double roundToHalf(double d) {
        return Math.round(d * 2) / 2.0;
    }

    @Override
    protected double calculateProbability(double max, double exceedance) {
        return 0;
    }
}
