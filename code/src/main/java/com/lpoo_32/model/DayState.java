package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ThirstOVF;

public class DayState extends NourishState {
    public DayState(PlayerModel player) {
        super(player);
    }

    @Override
    public void update(int time) throws HungerOVF, HealthOVF, ThirstOVF {
    }
}
