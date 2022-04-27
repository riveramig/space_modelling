package org.javeriana.world.layer.evapotranspiration;

import org.javeriana.automata.core.cell.GenericWorldCell;

public class EvapotranspirationCell extends GenericWorldCell<EvapotranspirationCellState> {

    private String id;

    public EvapotranspirationCell(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

}
