package org.javeriana.world.layer;

import org.javeriana.automata.core.cell.Cell;
import org.javeriana.automata.core.layer.GenericUniqueCellLayer;
import org.javeriana.util.WorldConfiguration;
import org.javeriana.world.helper.MonthlyDataLoader;
import org.javeriana.world.layer.data.MonthData;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public abstract class SimWorldSimpleLayer<C extends Cell> extends GenericUniqueCellLayer<C> {

    protected List<MonthData> monthlyData;

    protected Random random;

    protected WorldConfiguration worldConfig =  WorldConfiguration.getPropsInstance();

    public SimWorldSimpleLayer(String dataFile) {
        this.loadYearDataFromFile(dataFile);
        this.random = new Random();
    }

    public double calculateRandomFromMonthData(int month) {
        MonthData monthData = this.monthlyData.get(month);
        return monthData.getAverage()-monthData.getStandardDeviation() + (((monthData.getAverage()+monthData.getStandardDeviation()) - (monthData.getAverage()-monthData.getStandardDeviation())) * this.random.nextDouble());
    }

    protected void loadYearDataFromFile(String dataFile) {
        try{
            this.monthlyData = MonthlyDataLoader.loadMonthlyDataFile(dataFile);
        } catch(IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        }
    }

}
