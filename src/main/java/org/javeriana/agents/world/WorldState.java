package org.javeriana.agents.world;

import BESA.Kernel.Agent.StateBESA;
import org.javeriana.world.LayerExecutor;
import org.javeriana.world.layer.LayerFunctionParams;
import org.javeriana.world.layer.crop.CropLayer;
import org.javeriana.world.layer.disease.DiseaseLayer;
import org.javeriana.world.layer.evapotranspiration.EvapotranspirationLayer;
import org.javeriana.world.layer.rainfall.RainfallLayer;
import org.javeriana.world.layer.shortWaveRadiation.ShortWaveRadiationLayer;
import org.javeriana.world.layer.temperature.TemperatureLayer;

/**
 * Class that holds the world state, in this case the cellular automaton layers
 */
public class WorldState extends StateBESA {
    private TemperatureLayer temperatureLayer;
    private ShortWaveRadiationLayer shortWaveRadiationLayer;
    private CropLayer cropLayer;
    private DiseaseLayer diseaseLayer;
    private EvapotranspirationLayer evapotranspirationLayer;
    private RainfallLayer rainfallLayer;
    private LayerExecutor layerExecutor = new LayerExecutor();

    public WorldState(
            TemperatureLayer temperatureLayer,
            ShortWaveRadiationLayer shortWaveRadiationLayer,
            CropLayer cropLayer,
            DiseaseLayer diseaseLayer,
            EvapotranspirationLayer evapotranspirationLayer,
            RainfallLayer rainfallLayer) {
        this.temperatureLayer = temperatureLayer;
        this.shortWaveRadiationLayer = shortWaveRadiationLayer;
        this.cropLayer = cropLayer;
        this.diseaseLayer = diseaseLayer;
        this.evapotranspirationLayer = evapotranspirationLayer;
        this.rainfallLayer = rainfallLayer;
        layerExecutor.addLayer(this.shortWaveRadiationLayer,this.temperatureLayer,this.evapotranspirationLayer,this.rainfallLayer,this.diseaseLayer,this.cropLayer);
    }

    public TemperatureLayer getTemperatureLayer() {
        return temperatureLayer;
    }

    public ShortWaveRadiationLayer getShortWaveRadiationLayer() {
        return shortWaveRadiationLayer;
    }

    public CropLayer getCropLayer() {
        return cropLayer;
    }

    public DiseaseLayer getDiseaseLayer() {
        return diseaseLayer;
    }

    public EvapotranspirationLayer getEvapotranspirationLayer() {
        return evapotranspirationLayer;
    }

    public RainfallLayer getRainfallLayer() {
        return rainfallLayer;
    }

    public LayerExecutor getLayerExecutor() {
        return layerExecutor;
    }

    public void lazyUpdateCropsForDate(String eventDate) {
        this.layerExecutor.executeLayers(new LayerFunctionParams(eventDate));
    }
}
