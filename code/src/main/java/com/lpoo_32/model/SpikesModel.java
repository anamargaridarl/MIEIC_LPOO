package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ThirstOVF;

public class SpikesModel extends InteractableElement {

    public SpikesModel(int value, Position position){
        super(position, value);
    }

    @Override
    public void interact(PlayerModel player) throws HealthOVF, HungerOVF, ThirstOVF {
        player.getHealth().decreaseValue(this.getValue());
    }

}
