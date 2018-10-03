package com.gmail.cerzbbz.Tasks;

import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public abstract class FisherTask extends Task {
    private boolean isNotAnimating() {
        return !Players.getLocal().isAnimating();
    }
    protected final boolean hasInvSpace() { return !Inventory.isFull(); }

    @Override
    public final boolean validate() {
        return isNotAnimating() && hasFishingRod() && hasBait() && validateTask();
    }

    private boolean hasFishingRod() {
        return Inventory.contains("Fly fishing rod");
    }

    private boolean hasBait() {
        return Inventory.contains("Feather");
    }

    public abstract boolean validateTask();
}
