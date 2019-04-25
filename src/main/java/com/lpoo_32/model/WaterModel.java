package com.lpoo_32.model;


import com.lpoo_32.exceptions.HungerRestored;
import com.lpoo_32.exceptions.ThirstRestored;

public class WaterModel extends InteractableElement {

    public WaterModel(Position pos) {
        super(pos);
    }

    @Override
    public void interact(PlayerModel player) throws HungerRestored, ThirstRestored {
        player.getWater().increaseValue(20);
    }
}
