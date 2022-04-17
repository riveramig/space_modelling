package org.javeriana.environment_reference;

public class Wind extends WeatherObject{

    private double windSpeed;

    public Wind(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    protected double calculateProbability(double max, double exceedance) {
        return 0;
    }
}
