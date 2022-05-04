package org.javeriana.world.layer.shortWaveRadiation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.util.WorldConfiguration;
import org.javeriana.world.helper.DateHelper;
import org.javeriana.world.helper.ExtraterrestrialRadiation;
import org.javeriana.world.helper.Hemisphere;
import org.javeriana.world.layer.LayerFunctionParams;
import org.javeriana.world.layer.SimWorldSimpleLayer;
import org.javeriana.world.layer.data.MonthData;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ShortWaveRadiationLayer extends SimWorldSimpleLayer<ShortWaveRadiationCell> {

    private static final Logger logger = LogManager.getLogger(ShortWaveRadiationLayer.class);

    private double a_s = 0.25;
    private double b_s = 0.5;
    private final double albedoReflection = 0.23;

    private Hemisphere hemisphere;
    private double [] monthlyExtraterrestrialRadiationForLocation;

    private int latitudeDegrees;

    private WorldConfiguration worldConfig =  WorldConfiguration.getPropsInstance();

    public ShortWaveRadiationLayer(String datafile, Hemisphere hemisphere, int latitudeDegrees) {
        super(datafile);
        this.hemisphere = hemisphere;
        this.latitudeDegrees = latitudeDegrees;
        this.cell = new ShortWaveRadiationCell("radCell");
        this.setupLayer();
    }

    @Override
    public void setupLayer() {
        if(this.hemisphere == Hemisphere.NORTHERN) {
            this.monthlyExtraterrestrialRadiationForLocation = ExtraterrestrialRadiation.getNorthernData().get(
                    this.latitudeDegrees % 2 == 0 ? this.latitudeDegrees : this.latitudeDegrees + 1
            );
        } else {
            this.monthlyExtraterrestrialRadiationForLocation = ExtraterrestrialRadiation.getSouthernData().get(
                    this.latitudeDegrees % 2 == 0 ? this.latitudeDegrees : this.latitudeDegrees + 1
            );
        }
    }

    @Override
    public void executeLayer() {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void executeLayer(LayerExecutionParams params) {
        LayerFunctionParams params1 = (LayerFunctionParams) params;
        if(this.cell.getCellState() == null) {
            int monthFromDate = DateHelper.getMonthFromStringDate(params1.getDate());
            double nextShortWaveRadiationRate = this.calculateNetShortWaveRadiationForMonth(monthFromDate);
            this.cell.setCellState(params1.getDate(),
                    new ShortWaveRadiationCellState(nextShortWaveRadiationRate)
            );
        } else {
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern(this.worldConfig.getProperty("date.format"));
            int daysBetweenLastDataAndNewEvent = DateHelper.differenceDaysBetweenTwoDates(this.cell.getDate(),params1.getDate());
            for (int i = 0; i < daysBetweenLastDataAndNewEvent; i++) {
                DateTime previousStateDate = DateHelper.getDateInJoda(this.cell.getDate());
                DateTime previousStateDatePlusOneDay = previousStateDate.plusDays(1);
                int month = previousStateDatePlusOneDay.getMonthOfYear()-1;
                String newDate = dtfOut.print(previousStateDatePlusOneDay);
                this.cell.setCellState(newDate, new ShortWaveRadiationCellState(this.calculateNetShortWaveRadiationForMonth(month)));
            }
        }
    }

    private double calculateNetShortWaveRadiationForMonth(int month) {
        return (1-this.albedoReflection) * this.calculateShortWaveRadiation(month);
    }

    private double calculateShortWaveRadiation(int month) {
        MonthData monthData = this.monthlyData.get(month);
        return (this.a_s + this.b_s * (this.calculateGaussianFromMonthData(month)/monthData.getMaxValue())) * this.monthlyExtraterrestrialRadiationForLocation[month];
    }


}
