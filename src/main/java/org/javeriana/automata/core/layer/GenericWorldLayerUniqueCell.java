package org.javeriana.automata.core.layer;

import org.javeriana.automata.core.cell.LayerCell;

public abstract class GenericWorldLayerUniqueCell<T extends LayerCell> extends GenericWorldLayer {

    protected T cell;

    public GenericWorldLayerUniqueCell() {}

    public GenericWorldLayerUniqueCell(T cell) {
        this.cell = cell;
    }

    public T getCell() {
        return cell;
    }

    public void setCell(T cell) {
        this.cell = cell;
    }
}
