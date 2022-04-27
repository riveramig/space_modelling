package org.javeriana.world.layer.rainfall;

import org.javeriana.automata.core.cell.GenericWorldCell;

public class RainfallCell extends GenericWorldCell<RainfallCellState> {

    private String id;

    public RainfallCell(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return null;
    }

    public void setId(String id) {
        this.id = id;
    }
}
