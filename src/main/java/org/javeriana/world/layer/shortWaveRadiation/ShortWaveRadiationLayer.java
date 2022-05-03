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

import java.text.ParseException;

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
        String dateFormat = this.worldConfig.getProperty("date.format");
        try {
            int monthFromDate = DateHelper.getMonthFromStringDate(params1.getDate(), dateFormat);
            double nextShortWaveRadiationRate = this.calculateNetShortWaveRadiationForMonth(monthFromDate);
            logger.info("Next short wave radiation rate: "+nextShortWaveRadiationRate);
            this.cell.setCellState(params1.getDate(),
                    new ShortWaveRadiationCellState(nextShortWaveRadiationRate)
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
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
