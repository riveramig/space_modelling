package org.javeriana.world.layer.rainfall;

import org.javeriana.automata.core.layer.GenericUniqueCellLayer;
import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.util.WorldConfiguration;
import org.javeriana.world.helper.DateHelper;
import org.javeriana.world.helper.MonthlyDataLoader;
import org.javeriana.world.layer.LayerFunctionParams;
import org.javeriana.world.layer.data.MonthData;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

public class RainfallLayer extends GenericUniqueCellLayer<RainfallCell> {

    private List<MonthData> monthlyData;
    private Random random;

    private WorldConfiguration worldConfig =  WorldConfiguration.getPropsInstance();

    public RainfallLayer() {
        this.random = new Random();
        this.cell = new RainfallCell("rainCell");
        this.setupLayer();
    }

    @Override
    public void setupLayer() {
        try {
            this.monthlyData = MonthlyDataLoader.loadMonthlyDataFile(this.worldConfig.getProperty("data.rainfall"));
        } catch(IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        }
    }

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
            this.cell.setCellState(params1.getDate(), new RainfallCellState(this.calculateRainFromMonthData(monthFromDate)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private double calculateRainFromMonthData(int month) {
        MonthData monthData = this.monthlyData.get(month);
        return monthData.getAverage()-monthData.getStandardDeviation() + (((monthData.getAverage()+monthData.getStandardDeviation()) - (monthData.getAverage()-monthData.getStandardDeviation())) * this.random.nextDouble());
    }
}
