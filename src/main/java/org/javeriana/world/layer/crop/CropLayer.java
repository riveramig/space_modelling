package org.javeriana.world.layer.crop;

import org.javeriana.automata.core.layer.GenericMatrixCellLayer;
import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.world.layer.crop.cell.CropCell;
import org.javeriana.world.layer.shortWaveRadiation.ShortWaveRadiationCellState;
import org.javeriana.world.layer.shortWaveRadiation.ShortWaveRadiationLayer;

public class CropLayer extends GenericMatrixCellLayer<CropCell> {

    public CropLayer(CropCell[][] cellMatrix) {
        super(cellMatrix);
    }

    @Override
    public void setupLayer() {

    }

    @Override
    public void executeLayer() {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public <P extends LayerExecutionParams> void executeLayer(P params) {
        ShortWaveRadiationLayer radiationLayer = (ShortWaveRadiationLayer) this.dependantLayers.get("radiationLayer");
        double currentShortWaveRad = ((ShortWaveRadiationCellState)radiationLayer.getCell().getCellState()).getShortWaveRadiation();
    }
}
