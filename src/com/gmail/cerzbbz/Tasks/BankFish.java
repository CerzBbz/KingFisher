package com.gmail.cerzbbz.Tasks;

import com.gmail.cerzbbz.Helpers.PositionHelper;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;

public class BankFish extends FisherTask {
    @Override
    public int execute() {
        if (!Bank.isOpen()) {
            Bank.open();
            return Random.polar(1000, 1666);
        }
        Bank.depositAllExcept("Fly fishing rod", "Feather");
        Time.sleep(400);
        Bank.close();
        return Random.polar(682, 1235);
    }

    @Override
    public boolean validateTask() {
        return !super.hasInvSpace() && PositionHelper.atBank.getAsBoolean();
    }


}
