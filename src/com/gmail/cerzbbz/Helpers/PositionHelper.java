package com.gmail.cerzbbz.Helpers;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;

import java.util.function.BooleanSupplier;

public class PositionHelper {

    public static final Area FISHING_AREA = Area.rectangular(3100, 3428, 3105,  3433);

    public static BooleanSupplier atBank = () -> BankLocation.getNearest().getPosition().distance(Players.getLocal().getPosition()) <= 10;
    public static BooleanSupplier atFish = () -> FISHING_AREA.getCenter().distance(Players.getLocal().getPosition()) <= 15;
}
