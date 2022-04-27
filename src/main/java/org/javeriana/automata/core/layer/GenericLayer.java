package org.javeriana.automata.core.layer;

import java.util.HashMap;

public abstract class GenericLayer implements Layer{

    protected HashMap<String,Layer> dependantLayers;

    public GenericLayer(){
        this.dependantLayers = new HashMap<>();
    }

    @Override
    public <U extends Layer> void bindLayer(String layerName, U layer) {
        this.dependantLayers.put(layerName, layer);
    }

    @Override
    public <U extends Layer> U getLayer(String layerName) {
        return (U) this.dependantLayers.get(layerName);
    }
}
