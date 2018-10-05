package com.gmail.cerzbbz.Tasks;

import com.gmail.cerzbbz.Helpers.PositionHelper;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.ui.Log;

public class Fish extends FisherTask {
    @Override
    public int execute() {
        Time.sleep(Random.polar(260, 5500));

        Log.info("We're going to try to fish");
        Npc fishingSpot = Npcs.getNearest("Rod Fishing Spot");
        fishingSpot.interact("Lure");

        return 0;
    }

    @Override
    public boolean validateTask() {
        return super.hasInvSpace() && PositionHelper.atFish.getAsBoolean();
    }

    @Override
    public String getActionName() {
        return "Fishing";
    }
}
