package com.lpoo_32.model;


import com.googlecode.lanterna.Symbols;
import com.lpoo_32.exceptions.HungerRestored;
import com.lpoo_32.exceptions.ThirstRestored;

public class WaterModel extends CatchableElement {

    public WaterModel(int value, Position pos) {
        super(pos, value);
    }

    @Override
    public void interact(PlayerModel player) throws HungerRestored, ThirstRestored {
        player.getWater().increaseValue(20);
    }

}
