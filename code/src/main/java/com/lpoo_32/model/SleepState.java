package com.lpoo_32.model;

import com.lpoo_32.exceptions.*;

public class SleepState extends NourishState {
    public SleepState(PlayerModel player) {
        super(player);
    }

    @Override
    public void update(int time) throws HealthOVF, ThirstOVF, HungerRestored, ThirstRestored, HungerOVF, Sleeptime {
        player.getHealth().increaseValue(20);
        player.getFood().decreaseValue(20);
        player.getWater().decreaseValue(20);
        throw new Sleeptime();
    }
}
