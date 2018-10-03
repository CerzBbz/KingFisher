package com.gmail.cerzbbz.Tasks.Movement;

import com.gmail.cerzbbz.Helpers.PositionHelper;

import org.rspeer.runetek.api.movement.position.Position;

public class ToFish extends Movementate {

    @Override
    public Position getTargetPosition() {
        return PositionHelper.FISHING_AREA.getCenter();
    }

    @Override
    public boolean validateTask() {
        return super.hasInvSpace() && !PositionHelper.atFish.getAsBoolean();
    }
}
