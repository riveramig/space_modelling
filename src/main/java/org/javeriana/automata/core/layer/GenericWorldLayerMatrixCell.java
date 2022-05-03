package org.javeriana.automata.core.layer;

import org.javeriana.automata.core.cell.LayerCell;

public abstract class GenericWorldLayerMatrixCell<T extends LayerCell> extends GenericWorldLayer {

    protected T[][] cellMatrix;

    public GenericWorldLayerMatrixCell(T[][] cellMatrix) {
        this.cellMatrix = cellMatrix;
    }

    public GenericWorldLayerMatrixCell() {}

    public T[][] getCellMatrix() {
        return cellMatrix;
    }

    public void setCellMatrix(T[][] cellMatrix) {
        this.cellMatrix = cellMatrix;
    }
}
