package com.lpoo_32.model;


import com.lpoo_32.exceptions.HungerRestored;
import com.lpoo_32.exceptions.ThirstRestored;

public class WaterModel extends CatchableElement {

    private int value;
    WaterModel(int value,Position pos) {
        super(pos);
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void interact(PlayerModel player) throws HungerRestored, ThirstRestored {
        player.getWater().increaseValue(20);
    }
}
