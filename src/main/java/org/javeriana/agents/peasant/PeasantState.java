package org.javeriana.agents.peasant;

import BESA.Kernel.Agent.StateBESA;

public class PeasantState extends StateBESA {
    private String cropId;

    public PeasantState(String cropId) {
        this.cropId = cropId;
    }

    public String getCropId() {
        return cropId;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
    }
}
