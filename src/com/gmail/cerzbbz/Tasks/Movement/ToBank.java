package com.gmail.cerzbbz.Tasks.Movement;

import com.gmail.cerzbbz.Helpers.PositionHelper;
import com.gmail.cerzbbz.Tasks.FisherTask;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.ui.Log;

public class ToBank extends FisherTask {

    @Override
    public int execute() {
        Log.info("Attempting to walk to bank");
        Movement.walkToRandomized(BankLocation.getNearest().getPosition());
        return Random.polar(1111, 3333);
    }

    @Override
    public boolean validateTask() {
        return !super.hasInvSpace() && !PositionHelper.atBank.getAsBoolean();
    }
}
