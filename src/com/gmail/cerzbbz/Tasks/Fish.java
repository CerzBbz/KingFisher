package com.gmail.cerzbbz.Tasks;

import com.gmail.cerzbbz.Helpers.PositionHelper;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.ui.Log;

public class Fish extends FisherTask {
    @Override
    public int execute() {
        Log.info("We're going to try to fish");
        Npc fishingSpot = Npcs.getNearest("Rod Fishing Spot");
        fishingSpot.interact("Lure");
        return Random.polar(2222,3785);
    }

    @Override
    public boolean validateTask() {
        return super.hasInvSpace() && PositionHelper.atFish.getAsBoolean();
    }
}
