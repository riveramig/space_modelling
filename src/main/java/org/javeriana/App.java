package org.javeriana;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Log.ReportBESA;
import org.javeriana.environmentReference.*;
import org.javeriana.util.WorldConfiguration;
import org.javeriana.util.cropData.WorldCrops;
import org.javeriana.world.land.LandCellAutomata;
import org.javeriana.world.land.LandGuard;
import org.javeriana.world.land.LandState;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final double PSSWD = 0.91;

    public static void main( String[] args )
    {
        WorldConfiguration worldSettings = WorldConfiguration.getPropsInstance();

        WorldCrops worldCrops = WorldCrops.getInstance();

        Temperature temperature = new Temperature(worldSettings.getProperty("data.temperature"));
        RelativeHumidity relativeHumidity = new RelativeHumidity(worldSettings.getProperty("data.relativeHumidity"),temperature);
        Radiation radiation = new Radiation(Radiation.Hemisphere.SOUTHERN, 10, worldSettings.getProperty("data.radiation"), temperature, relativeHumidity);
        Atmospheric atmospheric = new Atmospheric(13);
        Wind wind = new Wind(1.10);
        SoilHeatFlux soilHeatFlux = new SoilHeatFlux(temperature);


        EvapotranspirationReference evapotranspirationReference = new EvapotranspirationReference(temperature,radiation,relativeHumidity,soilHeatFlux,wind,atmospheric);

        ClassLoader classLoader = App.class.getClassLoader();
        AdmBESA adm = AdmBESA.getInstance();
        try {
            LandState landState = new LandState();
            StructBESA structLand = new StructBESA();
            structLand.bindGuard(LandGuard.class);
            LandCellAutomata landCell = new LandCellAutomata("land1",landState,structLand,PSSWD);
        }catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
    }
}
