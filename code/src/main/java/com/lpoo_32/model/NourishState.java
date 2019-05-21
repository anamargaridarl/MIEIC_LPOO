package com.lpoo_32.model;

import com.lpoo_32.exceptions.*;

public abstract class NourishState {
    PlayerModel player;
    NourishState(PlayerModel player){
        this.player = player;
    }

    abstract public void update(int time) throws HungerOVF, HealthOVF, ThirstOVF, HungerRestored, ThirstRestored, Sleeptime;
}
