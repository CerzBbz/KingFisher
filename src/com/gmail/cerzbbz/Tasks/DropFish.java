package com.gmail.cerzbbz.Tasks;

import com.gmail.cerzbbz.PowerFisher;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.util.Arrays;

public class DropFish extends FisherTask{
    @Override
    public boolean validateTask() {
        return Inventory.isFull() && PowerFisher.shouldDropFish && !super.hasCookableFish();
    }

    @Override
    public int execute() {
        Time.sleep(Random.polar(250, 1500));

        for (int j = 0; j < 7; j++) {
            int i = 0;

            for (; i < 4; i++) {
                this.dropItemAtIndex((j == 0 ? 0 : j * 4) + i);
            }

            if (j != 6) {
                j++;

                for (i = 3; i >= 0; i--) {
                    this.dropItemAtIndex((j == 0 ? 0 : j * 4) + i);
                }
            }
        }

        return Random.polar(150, 2000);
    }

    public void dropItemAtIndex(int index) {
        Item item = Inventory.getItemAt(index);


        if (Arrays.binarySearch(PowerFisher.dropFish, item.getId()) >= 0) {
            item.interact("Drop");
            Time.sleep(Random.polar(200, 300));
        }
    }

    @Override
    public String getActionName() {
        return "Dropping Fish";
    }
}
