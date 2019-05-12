package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ThirstOVF;

public class FamishState extends NourishState {
    public FamishState(PlayerModel player) {
        super(player);
    }

    @Override
    public void update(int time) throws HungerOVF, HealthOVF, ThirstOVF {

        if ((time % 120) == 0) {
            this.player.getHealth().decreaseValue(5);
        }
    }
}
