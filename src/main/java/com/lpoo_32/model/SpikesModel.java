package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.NourishOVF;

public class SpikesModel extends InteractableElement {
    private int value;

    public SpikesModel(int value, Position position){
        super(position);
        this.value = value;
    }

    @Override
    public void interact(PlayerModel player) throws HealthOVF, NourishOVF {
        player.getHealth().decreaseValue(this.value);
    }

}
