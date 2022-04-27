package org.javeriana.automata.core.layer;

public interface Layer {

    void setupLayer();

    void executeLayer();

    <P extends LayerExecutionParams> void executeLayer(P params);

    <U extends Layer> void bindLayer(String layerName, U layer);

    <U extends Layer> U getLayer(String layerName);

}
