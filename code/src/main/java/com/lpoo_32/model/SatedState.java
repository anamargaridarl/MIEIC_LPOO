package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ThirstOVF;

public class SatedState extends NourishState {
    public SatedState(PlayerModel player) {
        super(player);
    }

    @Override
    public void update(int time) throws HungerOVF, HealthOVF, ThirstOVF {
        if(time % (5400.0) == 0) {
            this.player.getFood().decreaseValue(5);
        }
    }
}
