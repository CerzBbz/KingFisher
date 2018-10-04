package com.gmail.cerzbbz.Tasks.Movement;

import com.gmail.cerzbbz.Helpers.PositionHelper;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Position;

public class ToBank extends Movementate {

    @Override
    public Position getTargetPosition() {
        return BankLocation.getNearest().getPosition();
    }

    @Override
    public boolean validateTask() {
        return !super.hasInvSpace() && !PositionHelper.atBank.getAsBoolean();
    }

    @Override
    public String getActionName() {
        return "Walking to Bank";
    }
}
