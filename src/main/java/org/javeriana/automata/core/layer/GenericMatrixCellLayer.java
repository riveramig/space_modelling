package org.javeriana.automata.core.layer;

import org.javeriana.automata.core.cell.Cell;

public abstract class GenericMatrixCellLayer <T extends Cell> extends GenericLayer{

    protected T[][] cellMatrix;

    public GenericMatrixCellLayer(T[][] cellMatrix) {
        this.cellMatrix = cellMatrix;
    }

    public GenericMatrixCellLayer() {}

}
