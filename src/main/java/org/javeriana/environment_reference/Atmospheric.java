package org.javeriana.environment_reference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Atmospheric extends WeatherObject{

    private static final Logger logger = LogManager.getLogger(Atmospheric.class);

    /**
     * In meters above sea
     */
    private double altitude;
    private double psychrometricConstant;
    private double atmosphericPressure;

    public Atmospheric(double altitude) {
        this.altitude = altitude;
        this.atmosphericPressure = this.calculateAtmosphericPressure();
        logger.info("Atmospheric pressure: "+this.atmosphericPressure);
        this.psychrometricConstant = this.calculatePsychrometricConstant();
        logger.info("Psychrometric constant: "+this.psychrometricConstant);
    }

    /**
     * Calculate atmospheric pressure from FAO
     * @return
     */
    private double calculateAtmosphericPressure() {
        return 101.3*Math.pow((293-(0.0065*this.altitude))/293,5.26);
    }

    /**
     * Calculate psychrometric constant from FAO
     * @return
     */
    private double calculatePsychrometricConstant(){
        return 0.665e-3*this.atmosphericPressure;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getPsychrometricConstant() {
        return psychrometricConstant;
    }

    public double getAtmosphericPressure() {
        return atmosphericPressure;
    }

    @Override
    protected double calculateProbability(double max, double exceedance) {
        return 0;
    }
}
