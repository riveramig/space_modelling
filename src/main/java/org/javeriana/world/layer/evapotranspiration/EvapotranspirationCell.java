package org.javeriana.world.layer.evapotranspiration;

import org.javeriana.automata.core.cell.GenericWorldLayerCell;

/**
 * Concrete implementation for the evapotranspiration cell
 */
public class EvapotranspirationCell extends GenericWorldLayerCell<EvapotranspirationCellState> {

    private String id;

    public EvapotranspirationCell(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

}
