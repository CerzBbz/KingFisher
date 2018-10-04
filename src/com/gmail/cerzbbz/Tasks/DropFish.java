package com.gmail.cerzbbz.Tasks;

import com.gmail.cerzbbz.PowerFisher;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.util.regex.Pattern;

public class DropFish extends FisherTask{
    @Override
    public boolean validateTask() {
        return Inventory.isFull() && PowerFisher.shouldDropFish;
    }

    @Override
    public int execute() {
        Item[] items = Inventory.getItems(Pattern.compile("(Raw (salmon|trout))"));

        for(int i = 0, end = items.length; i < end; i++) {
            //Gonna be annoying here and pretend like we're people, dropping not in the right way
            if (Random.polar(0,9) == 4 && i + 4 < end) {
                items[i + 4].interact("Drop");
            }

            items[i].interact("Drop");

            Time.sleep(Random.polar(88, 300));
        }
        return Random.polar(150, 2000);
    }

    @Override
    public String getActionName() {
        return "Dropping Fish";
    }
}
