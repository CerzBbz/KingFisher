package com.gmail.cerzbbz;

import com.gmail.cerzbbz.Tasks.BankFish;
import com.gmail.cerzbbz.Tasks.CookFish;
import com.gmail.cerzbbz.Tasks.DropFish;
import com.gmail.cerzbbz.Tasks.Fish;
import com.gmail.cerzbbz.Tasks.Movement.ToBank;
import com.gmail.cerzbbz.Tasks.Movement.ToFish;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(developer = "Cerz", desc = "Fishing script", name = "FishFish", version = 0.1)
public class PowerFisher extends TaskScript {

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
    }
}
