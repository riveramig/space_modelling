package org.javeriana.world.layer.crop.cell.rice;

import org.javeriana.world.layer.crop.cell.CropCell;

public class RiceCell extends CropCell<RiceCellState> {

    private String id;

    public RiceCell(String id, double cropFactor_ini, double cropFactor_mid, double cropFactor_end, int widthCrop, int longCrop) {
        super(cropFactor_ini, cropFactor_mid, cropFactor_end, widthCrop, longCrop);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
