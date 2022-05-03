package org.javeriana.world.layer.disease;

import org.javeriana.automata.core.cell.GenericWorldLayerCell;

public class DiseaseCell extends GenericWorldLayerCell<DiseaseCellState> {

    private String id;

    public DiseaseCell(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
