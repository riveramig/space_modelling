package org.javeriana.automata.core.layer;

import org.javeriana.automata.core.cell.Cell;

public abstract class GenericUniqueCellLayer<T extends Cell> extends GenericLayer{

    protected T cell;

    public GenericUniqueCellLayer() {}

    public GenericUniqueCellLayer(T cell) {
        this.cell = cell;
    }

    public T getCell() {
        return cell;
    }

    public void setCell(T cell) {
        this.cell = cell;
    }
}
