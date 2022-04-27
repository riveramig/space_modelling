package org.javeriana.automata.core.cell;

import java.util.HashMap;

/**
 *
 * This is a generic abstract implementation of a cell that holds a history of states
 * whenever they change
 *
 * @param <S> type of state of the cell
 */
public abstract class GenericWorldCell<S extends CellState> implements Cell {
    /**
     * Instance of the state of the cell
     */
    protected S cellState;
    /**
     * This structure holds all the states of the current cell when a new state is set for the current cell
     * is stored, in this case with the date in string format.
     */
    protected HashMap<String, S> historicalStates = new HashMap<>();

    /**
     * retrieves the historical states for the cell
     * @return Hashmap with all the historic states of the cell
     */
    public HashMap<String, S> getHistoricalData () {
        return historicalStates;
    }

    /**
     * Retrieves a state for a given date, null if no state for a given date
     * @param date String date
     * @return State
     */
    public S getCellStateByDate (String date) {
        return this.historicalStates.get(date);
    }

    /**
     * Sets a new state in the cell also stores it in the hasmap structure
     * @param date date in string format
     * @param cellState new cell state
     */
    public void setCellState(String date, S cellState) {
        this.cellState = cellState;
        this.historicalStates.put(date, cellState);
    }

    /**
     * Retrieves the current cell state
     * @return CellState
     */
    @Override
    public CellState getCellState() {
        return this.cellState;
    }
}
