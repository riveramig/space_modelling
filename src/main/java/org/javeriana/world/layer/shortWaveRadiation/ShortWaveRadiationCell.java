package org.javeriana.world.layer.shortWaveRadiation;

import org.javeriana.automata.core.cell.GenericWorldCell;


public class ShortWaveRadiationCell extends GenericWorldCell<ShortWaveRadiationCellState> {

    private String id;

    public ShortWaveRadiationCell(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

}
