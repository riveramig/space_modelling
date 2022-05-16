package org.javeriana.agents.world;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.agents.messages.peasant.PeasantMessage;
import org.javeriana.agents.messages.peasant.PeasantMessageType;
import org.javeriana.agents.messages.world.WorldMessage;
import org.javeriana.agents.peasant.PeasantGuard;
import org.javeriana.world.layer.crop.cell.CropCellState;
import org.json.JSONObject;

public class WorldGuard extends GuardBESA {
    private static final Logger logger = LogManager.getLogger(WorldGuard.class);

    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        WorldMessage worldMessage = (WorldMessage)eventBESA.getData();
        WorldState worldState = (WorldState) this.agent.getState();
        switch (worldMessage.getWorldMessageType()) {
            case CROP_INFORMATION:
                logger.info("Message received: Crop - "+worldMessage.getCropId()+" information "+worldMessage.getPayload()+ " date: "+worldMessage.getDate());
                worldState.lazyUpdateCropsForDate(worldMessage.getDate());
                CropCellState cropCellState = worldState.getCropLayer().getCropStateById(worldMessage.getCropId());
                String cropDataToJson = new JSONObject(cropCellState).toString();
                PeasantMessage peasantMessage = new PeasantMessage(PeasantMessageType.NOTIFY_CROP_INFORMATION, worldMessage.getPeasantAgentId(),cropDataToJson);
                this.replyToPeasantAgent(worldMessage.getPeasantAgentId(), peasantMessage);
                break;
        }
    }

    public void replyToPeasantAgent(String peasantAgentId, PeasantMessage peasantMessage) {
        try {
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAid(peasantAgentId);
            EventBESA event = new EventBESA(PeasantGuard.class.getName(),peasantMessage);
            ah.sendEvent(event);
        }catch (ExceptionBESA exceptionBESA) {
            exceptionBESA.printStackTrace();
        }
    }
}
