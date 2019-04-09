package com.lpoo_32.logic;

import com.lpoo_32.exceptions.StatusOverflow;

public class Spikes implements InteractableElement {
    int value;

    Spikes(int value){
        this.value = value;
    }

    @Override
    public void interact(PlayerModel player) throws StatusOverflow {
        player.getHealth().decreaseValue(this.value);
        player.getWater().decreaseValue(this.value);
        player.getFood().decreaseValue(this.value);
    }
}
