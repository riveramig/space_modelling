package org.javeriana.world.layer.temperature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.world.helper.DateHelper;
import org.javeriana.world.layer.LayerFunctionParams;
import org.javeriana.world.layer.SimWorldSimpleLayer;
import org.javeriana.world.layer.rainfall.RainfallCellState;

import java.text.ParseException;

public class TemperatureLayer extends SimWorldSimpleLayer<TemperatureCell> {

    private static final Logger logger = LogManager.getLogger(TemperatureLayer.class);

    public TemperatureLayer(String dataFile) {
        super(dataFile);
        this.cell = new TemperatureCell("tempCell");
    }

    @Override
    public void setupLayer() {}

    @Override
    public void executeLayer() {}

    @Override
    public void executeLayer(LayerExecutionParams params) {
        LayerFunctionParams params1 = (LayerFunctionParams) params;
        String dateFormat = this.worldConfig.getProperty("date.format");
        try {
            int monthFromDate = DateHelper.getMonthFromStringDate(params1.getDate(), dateFormat);
            double nextTemperatureRate = this.calculateGaussianFromMonthData(monthFromDate);
            logger.info("Next evapotranspiration rate: "+nextTemperatureRate);
            this.cell.setCellState(params1.getDate(), new RainfallCellState(nextTemperatureRate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
