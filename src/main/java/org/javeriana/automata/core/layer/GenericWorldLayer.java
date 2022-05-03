package org.javeriana.automata.core.layer;

import java.util.HashMap;

public abstract class GenericWorldLayer implements WorldLayer {

    protected HashMap<String, WorldLayer> dependantLayers;

    public GenericWorldLayer(){
        this.dependantLayers = new HashMap<>();
    }

    @Override
    public void bindLayer(String layerName, WorldLayer layer) {
        this.dependantLayers.put(layerName, layer);
    }

    @Override
    public WorldLayer getLayer(String layerName) {
        return this.dependantLayers.get(layerName);
    }
}
