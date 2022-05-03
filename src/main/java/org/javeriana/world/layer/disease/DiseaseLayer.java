package org.javeriana.world.layer.disease;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.automata.core.layer.GenericWorldLayerGraphCell;
import org.javeriana.automata.core.layer.LayerExecutionParams;
import org.javeriana.util.WorldConfiguration;
import org.javeriana.world.layer.LayerFunctionParams;
import org.jgrapht.Graphs;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DiseaseLayer extends GenericWorldLayerGraphCell<DiseaseCell> {

    private static final Logger logger = LogManager.getLogger(DiseaseLayer.class);
    private HashMap<String, DiseaseCell> cellDirectory;
    private WorldConfiguration worldConfig =  WorldConfiguration.getPropsInstance();

    public DiseaseLayer() {
        this.cellDirectory = new HashMap<>();
    }

    public void addVertex(DiseaseCell cell) {
        this.cellDirectory.put(cell.getId(),cell);
        this.simpleCellGraph.addVertex(cell);
    }

    public void addEdge(DiseaseCell cell1, DiseaseCell cell2) {
        this.simpleCellGraph.addEdge(cell1, cell2);
    }

    /**
     * Adds insecticide of a specified crop in the next layer run this will affect probabilities
     * @param cellId id of the cell
     * @param percentageOfCoverage percentage of coverage of the crop that was cover with insecticide
     */
    public void addInsecticideToCell(String cellId, double percentageOfCoverage){

    }

    @Override
    public void setupLayer() {}

    @Override
    public void executeLayer() {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void executeLayer(LayerExecutionParams params) {
        LayerFunctionParams params1 = (LayerFunctionParams) params;
        String dateFormat = this.worldConfig.getProperty("date.format");
        try {
            Date dateObj = new SimpleDateFormat(dateFormat).parse(params1.getDate());
        } catch (ParseException e) {
            logger.error("Error parsing the date parameters");
            throw new RuntimeException(e);
        }
        BreadthFirstIterator breadthFirstIterator = new BreadthFirstIterator(this.simpleCellGraph);
        while(breadthFirstIterator.hasNext()) {
            DiseaseCell currentCell = (DiseaseCell) breadthFirstIterator.next();
            DiseaseCellState currentCellState = (DiseaseCellState) currentCell.getCellState();
            double probabilityDiseaseConfigured = Double.parseDouble(this.worldConfig.getProperty("disease.incrementProbabilityPerDay"));
            //If first layer execution just creates the first state with the current date, for this case the probability of each cell always starts at 0
            if(currentCell.getCellState() == null) {
                DiseaseCellState newCellState = new DiseaseCellState();
                newCellState.setCurrentProbabilityDisease(probabilityDiseaseConfigured);
                newCellState.setPercentageOfInsecticide(0);
                newCellState.setInfected(false);
                currentCell.setCellState(params1.getDate(), newCellState);
            } else {
                double incrementNeighborInfected = Double.parseDouble(this.worldConfig.getProperty("disease.incrementNeighborInfected"));
                int quantityNeighborsInfected = 0;
                // If no first layer execution, then reviews current state if peasant added insecticide and neighbor cells, if not then runs the configured rule to increase the probability
                List<DiseaseCell> neighbors = Graphs.neighborListOf(this.simpleCellGraph, currentCell);
                for(DiseaseCell neighbor: neighbors) {
                    if(((DiseaseCellState)neighbor.getCellState()).isInfected()) {
                        quantityNeighborsInfected ++;
                    }
                }
                DiseaseCellState newCellState = new DiseaseCellState();
                newCellState.setCurrentProbabilityDisease(currentCellState.getCurrentProbabilityDisease()+probabilityDiseaseConfigured);
                newCellState.setPercentageOfInsecticide(0);
                // Evaluates if should get infected if the random is less or equal of the current accumulated probability of generating a disease plus the quantity of neighbors infected multiplied the configured factor
                newCellState.setInfected(this.random.nextDouble()<=currentCellState.getCurrentProbabilityDisease()+quantityNeighborsInfected*incrementNeighborInfected);
            }

        }
    }
}
