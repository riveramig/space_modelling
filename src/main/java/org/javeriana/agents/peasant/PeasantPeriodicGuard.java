package org.javeriana.agents.peasant;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javeriana.DateSingleton;
import org.javeriana.agents.messages.peasant.PeasantMessage;
import org.javeriana.agents.messages.peasant.PeasantMessageType;

import java.util.Random;

public class PeasantPeriodicGuard extends PeriodicGuardBESA {
    private Random random = new Random();
    private static final Logger logger = LogManager.getLogger(PeasantPeriodicGuard.class);

    @Override
    public void funcPeriodicExecGuard(EventBESA eventBESA) {
        PeasantState peasantState = (PeasantState) this.agent.getState();
        DateSingleton singleton = DateSingleton.getInstance();
        String currentDate = singleton.getCurrentDate();
        logger.info("Current date: "+currentDate);
        // Evaluate if the peasant will check the crop based on his probability
        if(this.random.nextDouble() <= peasantState.getProbabilityOfDailyCropSupervision()) {
            try {
                AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAid(this.agent.getAid());
                PeasantMessage peasantMessageRequestCropInformation = new PeasantMessage(PeasantMessageType.REQUEST_CROP_INFORMATION,ah.getAgId(), null);
                peasantMessageRequestCropInformation.setDate(currentDate);
                singleton.getDatePlusOneDayAndUpdate();
                EventBESA eventBESASend = new EventBESA(PeasantGuard.class.getName(),peasantMessageRequestCropInformation);
                ah.sendEvent(eventBESASend);
            }catch (ExceptionBESA exceptionBESA) {
                exceptionBESA.printStackTrace();
            }
        } else {
            singleton.getDatePlusOneDayAndUpdate();
        }
    }
}
