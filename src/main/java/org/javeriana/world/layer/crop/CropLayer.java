package org.javeriana.world.layer.crop;


import org.javeriana.automata.core.layer.GenericWorldLayer;
import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.util.WorldConfiguration;
import org.javeriana.world.helper.DateHelper;
import org.javeriana.world.layer.LayerFunctionParams;
import org.javeriana.world.layer.crop.cell.CropCell;
import org.javeriana.world.layer.crop.cell.CropCellState;
import org.javeriana.world.layer.disease.DiseaseCellState;
import org.javeriana.world.layer.evapotranspiration.EvapotranspirationCellState;
import org.javeriana.world.layer.evapotranspiration.EvapotranspirationLayer;
import org.javeriana.world.layer.rainfall.RainfallLayer;
import org.javeriana.world.layer.shortWaveRadiation.ShortWaveRadiationCellState;
import org.javeriana.world.layer.shortWaveRadiation.ShortWaveRadiationLayer;
import org.javeriana.world.layer.temperature.TemperatureCellState;
import org.javeriana.world.layer.temperature.TemperatureLayer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class CropLayer extends GenericWorldLayer {


    private List<CropCell> cropCellList = new ArrayList<>();
    private WorldConfiguration config = WorldConfiguration.getPropsInstance();

    @Override
    public void setupLayer() {}

    @Override
    public void executeLayer() {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void executeLayer(LayerExecutionParams params) {
        LayerFunctionParams paramsLayer = (LayerFunctionParams) params;
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern(this.config.getProperty("date.format"));
        TemperatureLayer temperatureLayer = (TemperatureLayer) this.dependantLayers.get("temperatureLayer");
        EvapotranspirationLayer evapotranspirationLayer = (EvapotranspirationLayer) this.dependantLayers.get("evapotranspirationLayer");
        ShortWaveRadiationLayer shortWaveRadiationLayer = (ShortWaveRadiationLayer) this.dependantLayers.get("shortWaveRadiationLayer");
        RainfallLayer rainfallLayer = (RainfallLayer) this.dependantLayers.get("rainfallLayer");
        this.cropCellList.parallelStream().filter(currentCell -> currentCell.isActive()).forEach(currentCell -> {
            CropCellState currentState = (CropCellState) currentCell.getCellState();
            if(currentState == null) {
                CropCellState newCellState = new CropCellState();
                newCellState.setEvapotranspiration(currentCell.getCropFactor_ini()*((EvapotranspirationCellState)evapotranspirationLayer.getCell().getCellState()).getEvapotranspirationReference());
                newCellState.setAboveGroundBiomass(0);
                newCellState.setGrowingDegreeDays(((TemperatureCellState)temperatureLayer.getCell().getCellState()).getTemperature());
                currentCell.setCellState(paramsLayer.getDate(),newCellState);
            } else {
                for (int i = 0; i < DateHelper.differenceDaysBetweenTwoDates(paramsLayer.getDate(), currentCell.getPreviousCellStateDate()); i++) {
                    CropCellState previousState = (CropCellState) currentCell.getPreviousCellState();
                    DateTime previousStateDate = DateHelper.getDateInJoda(currentCell.getPreviousCellStateDate());
                    String newDate = dtfOut.print(previousStateDate.plusDays(i+1));
                    CropCellState newCellState = new CropCellState();
                    newCellState.setGrowingDegreeDays(previousState.getGrowingDegreeDays()+((TemperatureCellState)temperatureLayer.getCell().getCellStateByDate(newDate)).getTemperature());
                    //------------------------------- growing degree days and k_c logic ----------------------------------------
                    double maximumRadiationEfficiency = Double.parseDouble(this.config.getProperty("agb.maximumRadiationEfficiency"));
                    double diseaseDamageCropFactor = Double.parseDouble(this.config.getProperty("disease.damagesCrop"));
                    double agbConversionFactor = Double.parseDouble(this.config.getProperty("agb.conversionFactor"));
                    if(newCellState.getGrowingDegreeDays()<currentCell.getDegreeDays_mid()) {
                        newCellState.setEvapotranspiration(
                                currentCell.getCropFactor_ini()*
                                (((EvapotranspirationCellState)evapotranspirationLayer.getCell().getCellStateByDate(newDate)).getEvapotranspirationReference())*
                                (((DiseaseCellState)currentCell.getDiseaseCell().getCellState()).isInfected() ? diseaseDamageCropFactor : 1)
                                //Missing water stress factor
                        );
                    }else if (newCellState.getGrowingDegreeDays()>=currentCell.getDegreeDays_mid() && currentState.getGrowingDegreeDays()<currentCell.getDegreeDays_end()) {
                        newCellState.setEvapotranspiration(currentCell.getCropFactor_mid()*((EvapotranspirationCellState)evapotranspirationLayer.getCell().getCellStateByDate(newDate)).getEvapotranspirationReference());
                    }else {
                        newCellState.setEvapotranspiration(currentCell.getCropFactor_end()*((EvapotranspirationCellState)evapotranspirationLayer.getCell().getCellStateByDate(newDate)).getEvapotranspirationReference());
                        //------------------------------------ alert peasant crop is ready to collect ---------------------------------------------------
                    }
                    newCellState.setAboveGroundBiomass(maximumRadiationEfficiency*newCellState.getEvapotranspiration()*((ShortWaveRadiationCellState)shortWaveRadiationLayer.getCell().getCellStateByDate(newDate)).getShortWaveRadiation()*agbConversionFactor);
                    currentCell.setCellState(newDate, newCellState);
                }
            }
        });
    }

    public void addCrop(CropCell crop) {
        this.cropCellList.add(crop);
    }
}
