package org.javeriana.world.layer.crop.cell.root;

import org.javeriana.world.layer.crop.cell.CropCell;

public class RootCell extends CropCell<RootCellState> {

    private String id;

    public RootCell(double cropFactor_ini, double cropFactor_mid, double cropFactor_end, double degreeDays_mid, double degreeDays_end, int widthCrop, int longCrop, String id) {
        super(cropFactor_ini, cropFactor_mid, cropFactor_end, degreeDays_mid, degreeDays_end, widthCrop, longCrop);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
