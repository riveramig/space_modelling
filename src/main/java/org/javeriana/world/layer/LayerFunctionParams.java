package org.javeriana.world.layer;

import org.javeriana.automata.core.layer.LayerExecutionParams;

public class LayerFunctionParams implements LayerExecutionParams {
    private String date;

    public LayerFunctionParams(String date) {
        this.date = date;
    }

    public LayerFunctionParams() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
