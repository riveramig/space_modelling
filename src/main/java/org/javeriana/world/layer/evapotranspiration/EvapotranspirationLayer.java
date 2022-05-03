package org.javeriana.world.layer.evapotranspiration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.world.helper.DateHelper;
import org.javeriana.world.layer.LayerFunctionParams;
import org.javeriana.world.layer.SimWorldSimpleLayer;

import java.text.ParseException;
import java.util.Random;

public class EvapotranspirationLayer extends SimWorldSimpleLayer<EvapotranspirationCell> {

    private static final Logger logger = LogManager.getLogger(EvapotranspirationLayer.class);

    public EvapotranspirationLayer(String dataFile) {
        super(dataFile);
        this.cell = new EvapotranspirationCell("evapoCell");
    }

    @Override
    public void setupLayer() {

    }

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
            double nextEvapotranspirationRate = this.calculateGaussianFromMonthData(monthFromDate);
            logger.info("Next evapotranspiration rate: "+nextEvapotranspirationRate);
            this.cell.setCellState(params1.getDate(), new EvapotranspirationCellState(nextEvapotranspirationRate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
