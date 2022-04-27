package org.javeriana.world.layer.rainfall;

import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.util.WorldConfiguration;
import org.javeriana.world.helper.DateHelper;
import org.javeriana.world.helper.MonthlyDataLoader;
import org.javeriana.world.layer.LayerFunctionParams;
import org.javeriana.world.layer.SimWorldSimpleLayer;
import org.javeriana.world.layer.data.MonthData;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

public class RainfallLayer extends SimWorldSimpleLayer<RainfallCell> {


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
    public <P extends LayerExecutionParams> void executeLayer(P params) {
        LayerFunctionParams params1 = (LayerFunctionParams) params;
        String dateFormat = this.worldConfig.getProperty("date.format");
        try {
            int monthFromDate = DateHelper.getMonthFromStringDate(params1.getDate(), dateFormat);
            this.cell.setCellState(params1.getDate(), new RainfallCellState(this.calculateRandomFromMonthData(monthFromDate)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
