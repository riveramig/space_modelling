package org.javeriana.agents.peasant;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.agents.messages.peasant.PeasantMessage;
import org.javeriana.agents.messages.world.WorldMessage;
import org.javeriana.agents.messages.world.WorldMessageType;
import org.javeriana.agents.world.WorldGuard;


public class PeasantGuard extends GuardBESA {

    private static final String WORLD_AGENT_ID = "worldsito";
    private static final Logger logger = LogManager.getLogger(PeasantGuard.class);
    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        PeasantMessage peasantMessage = (PeasantMessage) eventBESA.getData();
        PeasantState peasantState = (PeasantState) this.agent.getState();
        switch (peasantMessage.getPeasantMessageType()) {
            case REQUEST_CROP_INFORMATION:
                logger.info("Message received: Request crop info");
                WorldMessage worldMessage = new WorldMessage(
                        WorldMessageType.CROP_INFORMATION,
                        peasantState.getCropId(),
                        peasantMessage.getDate(),
                        this.agent.getAid());
                this.sendWorldAgentMessage(worldMessage);
                break;
            case NOTIFY_CROP_INFORMATION:
                logger.info("Crop info received: "+peasantMessage.getPayload());
                break;
        }
    }

    private void sendWorldAgentMessage(WorldMessage worldMessage) {
        try{
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(WORLD_AGENT_ID);
            EventBESA event = new EventBESA(WorldGuard.class.getName(),worldMessage);
            ah.sendEvent(event);
        }catch (ExceptionBESA exceptionBESA) {
            exceptionBESA.printStackTrace();
        }
    }
}
