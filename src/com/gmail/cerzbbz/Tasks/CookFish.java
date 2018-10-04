package com.gmail.cerzbbz.Tasks;

public class CookFish extends FisherTask {
    @Override
    public boolean validateTask() {
        return false;
    }

    @Override
    public int execute() {
        return 0;
    }

    @Override
    public String getActionName() {
        return "Cooking Fish";
    }
}
