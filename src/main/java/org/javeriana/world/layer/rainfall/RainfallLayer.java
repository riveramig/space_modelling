package org.javeriana.world.layer.rainfall;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.world.helper.DateHelper;
import org.javeriana.world.layer.LayerFunctionParams;
import org.javeriana.world.layer.SimWorldSimpleLayer;

import java.text.ParseException;

public class RainfallLayer extends SimWorldSimpleLayer<RainfallCell> {

    private static final Logger logger = LogManager.getLogger(RainfallLayer.class);

    public RainfallLayer(String dataFile) {
        super(dataFile);
        this.cell = new RainfallCell("rainCell");
    }


    @Override
    public void setupLayer() {}

    @Override
    public void executeLayer() {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void executeLayer(LayerExecutionParams params) {
        LayerFunctionParams params1 = (LayerFunctionParams) params;
        String dateFormat = this.worldConfig.getProperty("date.format");
        try {
            int monthFromDate = DateHelper.getMonthFromStringDate(params1.getDate(), dateFormat);
            double nextRainfallRate = this.calculateGaussianFromMonthData(monthFromDate);
            logger.info("Next rainfall rate: "+nextRainfallRate);
            this.cell.setCellState(params1.getDate(), new RainfallCellState(this.calculateGaussianFromMonthData(monthFromDate)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
