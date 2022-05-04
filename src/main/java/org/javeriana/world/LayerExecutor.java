package org.javeriana.world;

import org.javeriana.automata.core.layer.WorldLayer;
import org.javeriana.world.layer.LayerFunctionParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LayerExecutor {

    private List<WorldLayer>layers = new ArrayList<>();

    public LayerExecutor() {
    }

    public void addLayer(WorldLayer... layers) {
        this.layers.addAll(Arrays.asList(layers));
    }

    public void executeLayers(LayerFunctionParams params) {
        for(WorldLayer layer: this.layers) {
            layer.executeLayer(params);
        }
    }
}
