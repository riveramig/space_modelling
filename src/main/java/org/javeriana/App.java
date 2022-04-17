package org.javeriana;
import org.javeriana.environment_reference.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Temperature temperature = new Temperature("mariaLaBaja/temperature.data");
        RelativeHumidity relativeHumidity = new RelativeHumidity("mariaLaBaja/relativeHumidity.data",temperature);
        Radiation radiation = new Radiation(Radiation.Hemisphere.SOUTHERN, 10, "mariaLaBaja/radiation.data", temperature, relativeHumidity);
        Atmospheric atmospheric = new Atmospheric(13);
        Wind wind = new Wind(1.10);
        SoilHeatFlux soilHeatFlux = new SoilHeatFlux(temperature);


        EvapotranspirationReference evapotranspirationReference = new EvapotranspirationReference(temperature,radiation,relativeHumidity,soilHeatFlux,wind,atmospheric);

    }
}
