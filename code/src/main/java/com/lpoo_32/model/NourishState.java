package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ThirstOVF;

public abstract class NourishState {
    PlayerModel player;
    NourishState(PlayerModel player){
        this.player = player;
    }

    abstract public void update(int time) throws HungerOVF, HealthOVF, ThirstOVF;
}
