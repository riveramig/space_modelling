package org.javeriana.world.layer.shortWaveRadiation;

import org.javeriana.automata.core.cell.GenericWorldLayerCell;


public class ShortWaveRadiationCell extends GenericWorldLayerCell<ShortWaveRadiationCellState> {

    private String id;

    public ShortWaveRadiationCell(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

}
