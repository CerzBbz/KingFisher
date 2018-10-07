package com.gmail.cerzbbz;

import com.gmail.cerzbbz.Tasks.*;
import com.gmail.cerzbbz.Tasks.Movement.ToBank;
import com.gmail.cerzbbz.Tasks.Movement.ToFish;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ScriptMeta(developer = "R&R", desc = "Fishing script", name = "FishFish", version = 0.1)
public class PowerFisher extends TaskScript implements RenderListener {

    //Temporary Globals;
    public static boolean shouldBankFish = false;
    public static boolean shouldCookFish = true;
    public static boolean shouldDropFish = true;


    private long timeStarted;
    private int startingFishingXp;
    private int startingCookingXp;
    public int currentXp;

    public static int[] rawFish = {331, 335};
    public static long[] dropFish = {329, 331, 333, 335, 343};

    public static ArrayList<Integer> cookableFish = new ArrayList<>();

    private int cookingLevel;
    private static Map<Integer, Integer> cookingLevelMap = new HashMap<Integer, Integer>() {
        {
            put(331, 20);
            put(335, 25);
        }
    };


    @Override
    public void onResume() {
        makeCookableFishList();
        super.onResume();
    }

    private final Task[] TASKS = {
            new Fish(),
            new BankFish(),
            new CookFish(),
            new DropFish(),
            new ToBank(),
            new ToFish(),
    };

    @Override
    public void onStart() {
        submit(TASKS);
        this.setPaused(true);

        this.timeStarted = System.currentTimeMillis();
        this.startingFishingXp = Skills.getExperience(Skill.FISHING);
        this.startingCookingXp = Skills.getExperience(Skill.COOKING);
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        int offset = 200;

        Graphics g = renderEvent.getSource();

        Color c = new Color(255,40,201, 116);

        int boxHeight = 80;

        long timeRunningFor = (System.currentTimeMillis() - this.timeStarted) / 1000;

        NumberFormat format = NumberFormat.getInstance();

        int fishingXpEarnt = Skills.getExperience(Skill.FISHING) - startingFishingXp;
        long fishingXpHr = timeRunningFor >= 1 ? (fishingXpEarnt / timeRunningFor) * 60 * 60 : 0;
        int cookingXpEarnt = Skills.getExperience(Skill.COOKING) - startingCookingXp;
        long cookingXpHr = timeRunningFor >= 1 ? (cookingXpEarnt / timeRunningFor) * 60 * 60 : 0;

        ArrayList<String> stringsToPrint = new ArrayList<>();

        stringsToPrint.add("Currently " + FisherTask.LAST_ACTION_EXECUTED);
        stringsToPrint.add("Fishing");
        stringsToPrint.add("XP/HR: " + format.format(fishingXpHr));
        stringsToPrint.add("XP Gained: " + format.format(fishingXpEarnt));
        stringsToPrint.add("XP to level: " + format.format(Skills.getExperienceToNextLevel(Skill.FISHING)));

        if(shouldCookFish)
        {
            boxHeight = 160;
            stringsToPrint.add("");
            stringsToPrint.add("Cooking");
            stringsToPrint.add("XP/HR: " + format.format(cookingXpHr));
            stringsToPrint.add("XP Gained: " + format.format(cookingXpEarnt));
            stringsToPrint.add("XP to level: " + format.format(Skills.getExperienceToNextLevel(Skill.COOKING)));
        }

        g.setColor(c);
        g.fillRect(8, offset, 160, boxHeight);
        g.setColor(Color.pink);
        g.drawRect(8, offset, 160, boxHeight);
        g.setColor(Color.white);


        int i = 1;
        for(String stringToPrint : stringsToPrint) {
            g.drawString(stringToPrint, 16, offset + (16 * i));
            i++;
        }
    }

    private boolean canCookFish(int rawFishId) {
        return this.cookingLevel >= cookingLevelMap.get(rawFishId);
    }

    private void makeCookableFishList() {
        cookableFish.clear();
        if (!shouldCookFish) {
            return;
        }
        if (Game.isLoggedIn()) {
            this.cookingLevel = Skills.getLevel(Skill.COOKING);
            for (int rawFishId : rawFish) {
                if (canCookFish(rawFishId)) {
                    cookableFish.add(rawFishId);
                }
            }
        }
    }
}