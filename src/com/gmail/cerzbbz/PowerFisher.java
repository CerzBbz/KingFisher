package com.gmail.cerzbbz;

import com.gmail.cerzbbz.Tasks.*;
import com.gmail.cerzbbz.Tasks.Movement.ToBank;
import com.gmail.cerzbbz.Tasks.Movement.ToFish;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

import java.awt.*;
import java.text.NumberFormat;

@ScriptMeta(developer = "Cerz", desc = "Fishing script", name = "FishFish", version = 0.1)
public class PowerFisher extends TaskScript implements RenderListener {

    //Temporary Globals;
    public static boolean shouldDropFish = true;

    public long timeStarted;
    public int startingXp;
    public int currentXp;

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
        this.startingXp = Skills.getExperience(Skill.FISHING);
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        Graphics g = renderEvent.getSource();

        g.setColor(Color.magenta);
        g.fillRect(8, 250, 160, 80);
        g.setColor(Color.pink);
        g.drawRect(8, 250, 160, 80);

        g.setColor(Color.white);

        int totalXpEarnt = Skills.getExperience(Skill.FISHING) - startingXp;
        long timeRunningFor = (System.currentTimeMillis() - this.timeStarted) / 1000;

        NumberFormat format = NumberFormat.getInstance();

        g.drawString("Currently " + FisherTask.LAST_ACTION_EXECUTED, 16, 268);
        g.drawString("XP/HR: " + format.format((totalXpEarnt / timeRunningFor) * 60 * 60), 16, 284);
        g.drawString("Gained: " + format.format(totalXpEarnt), 16, 300);
        g.drawString("Next Level in: " + format.format(Skills.getExperienceToNextLevel(Skill.FISHING)), 16, 316);
    }
}
