package com.gmail.cerzbbz.Tasks.Movement;

import com.gmail.cerzbbz.Helpers.PositionHelper;
import com.gmail.cerzbbz.Tasks.FisherTask;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.ui.Log;

public class ToFish extends FisherTask {
    @Override
    public int execute() {
        Log.info("Attempting to walk to fishing spot");
        Movement.walkToRandomized(PositionHelper.FISHING_AREA.getCenter());
        return Random.polar(1389, 4231);
    }

    @Override
    public boolean validateTask() {
        return super.hasInvSpace() && !PositionHelper.atFish.getAsBoolean();
    }
}
