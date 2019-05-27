package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ThirstOVF;

public class SpikesModel extends ValuableElement {

    public SpikesModel(int value, Position position){
        super(position, value, "spikes.png");
    }

    @Override
    public boolean interact(PlayerModel player) throws HealthOVF, HungerOVF, ThirstOVF {
        player.getHealth().decreaseValue(this.getValue());
        return true;
    }

}
