package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ThirstOVF;

public class QuenchedState extends NourishState {
    public QuenchedState(PlayerModel player) {
        super(player);
    }

    @Override
    public void update(int time) throws HungerOVF, HealthOVF, ThirstOVF {
        if(time % (3600) == 0) {
            this.player.getWater().decreaseValue(5);
        }
    }
}
