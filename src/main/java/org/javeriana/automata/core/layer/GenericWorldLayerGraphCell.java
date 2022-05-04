package org.javeriana.automata.core.layer;

import org.javeriana.automata.core.cell.LayerCell;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GenericWorldLayerGraphCell<C extends LayerCell> extends GenericWorldLayer {

    protected Graph<C, DefaultEdge> simpleCellGraph = new SimpleGraph<>(DefaultEdge.class);
    protected List<C> allCells = new ArrayList<>();
    protected Random random = new Random();

}
