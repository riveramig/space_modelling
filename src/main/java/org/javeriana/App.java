package org.javeriana;

import org.javeriana.util.WorldConfiguration;
import org.javeriana.world.LayerExecutor;
import org.javeriana.world.helper.Hemisphere;
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

    public static void main( String[] args )
    {
        WorldConfiguration worldConfiguration = WorldConfiguration.getPropsInstance();

        System.out.println(new Random().nextDouble());
        //-------------------------------------- Weather Layers ----------------------------------------------------------


        ShortWaveRadiationLayer radiationLayer = new ShortWaveRadiationLayer(
                worldConfiguration.getProperty("data.radiation"),
                Hemisphere.SOUTHERN,
                9);
        TemperatureLayer temperatureLayer = new TemperatureLayer(worldConfiguration.getProperty("data.temperature"));
        EvapotranspirationLayer evapotranspirationLayer = new EvapotranspirationLayer(worldConfiguration.getProperty("data.evapotranspiration"));
        RainfallLayer rainfallLayer = new RainfallLayer(worldConfiguration.getProperty("data.rainfall"));
        DiseaseLayer diseaseLayer = new DiseaseLayer();

        //-------------------------------------- Disease Layer -----------------------------------------------------------

        DiseaseCell root1DiseaseCell = new DiseaseCell("root1DiseaseCell");
        DiseaseCell root2DiseaseCell = new DiseaseCell("root2DiseaseCell");
        DiseaseCell rice1DiseaseCell = new DiseaseCell("rice1DiseaseCell");
        DiseaseCell rice2DiseaseCell = new DiseaseCell("rice2DiseaseCell");

        diseaseLayer.addVertex(root1DiseaseCell);
        diseaseLayer.addVertex(root2DiseaseCell);
        diseaseLayer.addVertex(rice1DiseaseCell);
        diseaseLayer.addVertex(rice2DiseaseCell);

        diseaseLayer.addEdge(root1DiseaseCell,rice1DiseaseCell);
        diseaseLayer.addEdge(root1DiseaseCell,root2DiseaseCell);
        diseaseLayer.addEdge(root2DiseaseCell,rice2DiseaseCell);

        //-------------------------------------- Crops -------------------------------------------------------------------

        RootCell root1 = new RootCell(
                0.5,
                1.10,
                0.95,
                1650,
                9900,
                20,
                true,
                root1DiseaseCell,
                "root_1"
                );
        RootCell root2 = new RootCell(
                0.5,
                1.10,
                0.95,
                1650,
                9900,
                30,
                true,
                root2DiseaseCell,
                "root_2"
        );
        RiceCell rice1 = new RiceCell(
                1.20,
                0.9,
                0.9,
                1512,
                3330,
                30,
                true,
                rice1DiseaseCell,
                "rice_1"
        );
        RiceCell rice2 = new RiceCell(
                1.20,
                0.9,
                0.9,
                1512,
                3330,
                15,
                true,
                rice2DiseaseCell,
                "rice_2"
        );


        CropLayer cropLayer = new CropLayer();
        cropLayer.addCrop(root1);
        cropLayer.addCrop(root2);
        cropLayer.addCrop(rice1);
        cropLayer.addCrop(rice2);

        cropLayer.bindLayer("radiation", radiationLayer);
        cropLayer.bindLayer("rainfall", rainfallLayer);
        cropLayer.bindLayer("temperature", temperatureLayer);
        cropLayer.bindLayer("evapotranspiration", evapotranspirationLayer);


        //----------------------------------- Layer executor -------------------------------
        LayerExecutor layerExecutor = new LayerExecutor();
        layerExecutor.addLayer(radiationLayer,temperatureLayer,evapotranspirationLayer,rainfallLayer,diseaseLayer,cropLayer);
        layerExecutor.executeLayers(new LayerFunctionParams("01/01/2022"));
        layerExecutor.executeLayers(new LayerFunctionParams("01/02/2022"));
        cropLayer.writeCropData();
    }




}
