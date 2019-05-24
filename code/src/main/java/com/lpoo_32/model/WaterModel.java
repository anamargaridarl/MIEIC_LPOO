package com.lpoo_32.model;


import com.googlecode.lanterna.Symbols;
import com.lpoo_32.exceptions.*;

public class WaterModel extends CatchableElement {

    public WaterModel(int value, Position pos) {
        super(pos, value);
    }

    @Override
    public void interact(PlayerModel player) throws HealthOVF, HungerRestored, HungerOVF, ThirstRestored, ThirstOVF {
        player.getWater().decreaseValue(this.getValue());
    }
}
