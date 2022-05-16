package org.javeriana;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import org.javeriana.agents.messages.peasant.PeasantMessage;
import org.javeriana.agents.messages.peasant.PeasantMessageType;
import org.javeriana.agents.peasant.PeasantAgent;
import org.javeriana.agents.peasant.PeasantGuard;
import org.javeriana.agents.peasant.PeasantState;
import org.javeriana.agents.world.WorldAgent;
import org.javeriana.agents.world.WorldGuard;
import org.javeriana.agents.world.WorldState;
import org.javeriana.util.WorldConfiguration;
import org.javeriana.world.helper.Hemisphere;
import org.javeriana.world.helper.Soil;
import org.javeriana.world.layer.LayerFunctionParams;
import org.javeriana.world.layer.crop.CropLayer;
import org.javeriana.world.layer.crop.cell.rice.RiceCell;
import org.javeriana.world.layer.crop.cell.root.RootCell;
import org.javeriana.world.layer.disease.DiseaseCell;
import org.javeriana.world.layer.disease.DiseaseLayer;
import org.javeriana.world.layer.evapotranspiration.EvapotranspirationLayer;
import org.javeriana.world.layer.rainfall.RainfallLayer;
import org.javeriana.world.layer.shortWaveRadiation.ShortWaveRadiationLayer;
import org.javeriana.world.layer.temperature.TemperatureLayer;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String MAIN_RICE_CROP_ID = "rice_1";
    private static final double PSSWD = 0.91;
    public static void main( String[] args )
    {
        AdmBESA adm = AdmBESA.getInstance();
        WorldAgent worldAgent = buildWorld();
        worldAgent.start();
        PeasantAgent peasantAgent = buildProPeasantAgent();
        peasantAgent.start();

        //----- first message query peasant world agent for crop information
        try{
            AgHandlerBESA ah = adm.getHandlerByAid(peasantAgent.getAid());
            PeasantMessage peasantMessageRequestCropInformation = new PeasantMessage(PeasantMessageType.REQUEST_CROP_INFORMATION,ah.getAgId(), null);
            peasantMessageRequestCropInformation.setDate("01/01/2022");
            EventBESA eventBESA = new EventBESA(PeasantGuard.class.getName(),peasantMessageRequestCropInformation);
            ah.sendEvent(eventBESA);
        } catch (ExceptionBESA exceptionBESA) {
            exceptionBESA.printStackTrace();
        }
    }

    private static WorldAgent buildWorld() {
        WorldState worldState = buildWorldState();
        StructBESA structBESA = new StructBESA();
        structBESA.bindGuard(WorldGuard.class);
        try {
            WorldAgent worldAgent = new WorldAgent("worldsito", worldState,structBESA,PSSWD);
            return worldAgent;
        }catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
        return null;
    }

    private static WorldState buildWorldState() {
        WorldConfiguration worldConfiguration = WorldConfiguration.getPropsInstance();
        ShortWaveRadiationLayer radiationLayer = new ShortWaveRadiationLayer(
                worldConfiguration.getProperty("data.radiation"),
                Hemisphere.SOUTHERN,
                9);
        TemperatureLayer temperatureLayer = new TemperatureLayer(worldConfiguration.getProperty("data.temperature"));
        EvapotranspirationLayer evapotranspirationLayer = new EvapotranspirationLayer(worldConfiguration.getProperty("data.evapotranspiration"));
        RainfallLayer rainfallLayer = new RainfallLayer(worldConfiguration.getProperty("data.rainfall"));
        DiseaseLayer diseaseLayer = new DiseaseLayer();
        CropLayer cropLayer = new CropLayer();
        DiseaseCell diseaseCellRice = new DiseaseCell("rice1DiseaseCell");
        cropLayer.addCrop(new RootCell(
                1.20,
                0.9,
                0.9,
                1512,
                3330,
                100,
                0.9,
                0.2,
                Soil.LOAMY_SAND,
                true,
                diseaseCellRice,
                MAIN_RICE_CROP_ID
        ));
        cropLayer.bindLayer("radiation", radiationLayer);
        cropLayer.bindLayer("rainfall", rainfallLayer);
        cropLayer.bindLayer("temperature", temperatureLayer);
        cropLayer.bindLayer("evapotranspiration", evapotranspirationLayer);
        return new WorldState(temperatureLayer,radiationLayer,cropLayer,diseaseLayer,evapotranspirationLayer,rainfallLayer);
    }

    private static PeasantAgent buildProPeasantAgent() {
        PeasantState peasantState = new PeasantState(
                MAIN_RICE_CROP_ID
        );
        StructBESA structBESA= new StructBESA();
        structBESA.bindGuard(PeasantGuard.class);
        try {
            PeasantAgent peasantAgent = new PeasantAgent("peasantPro",peasantState, structBESA, PSSWD);
            return peasantAgent;
        }catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
        return null;
    }

    private static void buildLazyPeasantAgent() {

    }

    private static void buildNormalPeasantAgent() {

    }
}
