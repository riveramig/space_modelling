package org.javeriana.world.layer.rainfall;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.world.helper.DateHelper;
import org.javeriana.world.layer.LayerFunctionParams;
import org.javeriana.world.layer.SimWorldSimpleLayer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
        double rainThreshold = Double.parseDouble(this.worldConfig.getProperty("rainfall.minThreshold"));
        if(this.cell.getCellState() == null) {
            int monthFromDate = DateHelper.getMonthFromStringDate(params1.getDate());
            double newRainfallRate = this.calculateGaussianFromMonthData(monthFromDate);
            double verifyRainfall = newRainfallRate <= rainThreshold ? 0 : newRainfallRate;
            this.cell.setCellState(params1.getDate(), new RainfallCellState(verifyRainfall));
        } else {
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern(this.worldConfig.getProperty("date.format"));
            int daysBetweenLastDataAndNewEvent = DateHelper.differenceDaysBetweenTwoDates(this.cell.getDate(),params1.getDate());
            for (int i = 0; i < daysBetweenLastDataAndNewEvent ; i++) {
                DateTime previousStateDate = DateHelper.getDateInJoda(this.cell.getDate());
                DateTime previousStateDatePlusOneDay = previousStateDate.plusDays(1);
                int month = previousStateDatePlusOneDay.getMonthOfYear()-1;
                String newDate = dtfOut.print(previousStateDatePlusOneDay);
                double newRainfallRate = this.calculateGaussianFromMonthData(month);
                double verifyRainfall = newRainfallRate <= rainThreshold ? 0 : newRainfallRate;
                this.cell.setCellState(newDate, new RainfallCellState(verifyRainfall));
            }
        }
    }

}
