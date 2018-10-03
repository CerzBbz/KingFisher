package com.gmail.cerzbbz.Tasks.Movement;

import com.gmail.cerzbbz.Tasks.FisherTask;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.ui.Log;

public abstract class Movementate extends FisherTask {

    public abstract Position getTargetPosition();

    @Override
    public int execute() {
        Log.info("Attempting to walk");
        Movement.walkToRandomized(this.getTargetPosition());

        if (!Movement.isRunEnabled() && (Movement.getRunEnergy() / 100) > Math.random() + .45) {
            Movement.toggleRun(true);
        }

        return Random.polar(201, 4512);
    }
}
