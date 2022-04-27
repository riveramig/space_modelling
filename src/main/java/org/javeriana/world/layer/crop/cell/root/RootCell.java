package org.javeriana.world.layer.crop.cell.root;

import org.javeriana.world.layer.crop.cell.CropCell;

public class RootCell extends CropCell<RootCellState> {

    private String id;

    public RootCell(String id, double cropFactor_ini, double cropFactor_mid, double cropFactor_end, int widthCrop, int longCrop) {
        super(cropFactor_ini, cropFactor_mid, cropFactor_end, widthCrop, longCrop);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
