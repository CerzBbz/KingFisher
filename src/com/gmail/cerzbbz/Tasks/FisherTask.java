package com.gmail.cerzbbz.Tasks;

import com.gmail.cerzbbz.PowerFisher;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public abstract class FisherTask extends Task {
    public static String LAST_ACTION_EXECUTED = "Starting...";

    @Override
    public final boolean validate() {
        boolean isValid = isNotAnimating() && hasFishingRod() && hasBait() && validateTask();

        if (isValid) {
            LAST_ACTION_EXECUTED = this.getActionName();
        }

        return isValid;
    }

    public String getActionName() {
        return "";
    }

    private boolean hasFishingRod() {
        return Inventory.contains("Fly fishing rod");
    }

    private boolean hasBait() {
        return Inventory.contains("Feather");
    }

    private final boolean hasRawFish() { return Inventory.contains(PowerFisher.rawFish); }

    protected boolean isNotAnimating() {
        return !Players.getLocal().isAnimating();
    }

    protected final boolean hasInvSpace() {
        return !Inventory.isFull();
    }

    protected final boolean hasCookableFish() {
        if (!this.hasRawFish()) {
            return false;
        }
        boolean hasCookable = false;
        for (int rawFish : PowerFisher.cookableFish) {
            hasCookable = Inventory.contains(rawFish);
        }
        return hasCookable;
    }

    public abstract boolean validateTask();
}
