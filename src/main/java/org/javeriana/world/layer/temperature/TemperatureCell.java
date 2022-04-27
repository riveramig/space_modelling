package org.javeriana.world.layer.temperature;

import org.javeriana.automata.core.cell.CellState;
import org.javeriana.automata.core.cell.GenericWorldCell;

public class TemperatureCell extends GenericWorldCell<CellState> {


    private String id;


    public TemperatureCell(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
