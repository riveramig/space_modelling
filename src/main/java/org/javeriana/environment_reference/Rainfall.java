package org.javeriana.environment_reference;

public class Rainfall extends WeatherObject{



    @Override
    protected double calculateProbability(double max, double exceedance) {
        return 0;
    }
}
