package com.lpoo_32.model;

import com.lpoo_32.exceptions.StatusOverflow;

public class SpikesModel extends InteractableElement {
    private int value;

    public SpikesModel(int value, Position position){
        super(position);
        this.value = value;
    }

    @Override
    public void interact(PlayerModel player) throws StatusOverflow {
        player.getHealth().decreaseValue(this.value);
    }
}
