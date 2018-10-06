package com.gmail.cerzbbz.Tasks;

import com.gmail.cerzbbz.PowerFisher;
import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

public class CookFish extends FisherTask {
    @Override
    public boolean validateTask() {
        return PowerFisher.shouldCookFish && !super.hasInvSpace() && super.hasCookableFish();
    }

    @Override
    public int execute() {
        Time.sleep(Random.polar(260, 5500));

        Log.info("Trying to cook");

        for(int fishId : PowerFisher.cookableFish) {
            if(!Inventory.contains(fishId))
            {
                continue;
            }
            Item fish = Inventory.getFirst(fishId);
            fish.interact("Use");

            SceneObjects.getNearest(26185).interact("Use");

            while(Interfaces.getComponent(270, 14) == null){
                Time.sleep(Random.polar(800,2400));
            }
            InterfaceComponent cookFishButton = Interfaces.getComponent(270, 14);
            cookFishButton.interact(action -> true);

            Time.sleep(Random.mid(6000,18000));
        }

        return 0;
    }

    @Override
    public String getActionName() {
        return "Cooking Fish";
    }
}
