package org.javeriana.automata.core.layer;

public interface WorldLayer {

    void setupLayer();

    void executeLayer();

    void executeLayer(LayerExecutionParams params);

    void bindLayer(String layerName, WorldLayer layer);

    WorldLayer getLayer(String layerName);

}
