package com.lpoo_32.model;


import com.lpoo_32.exceptions.NourishRestored;

public class WaterModel extends InteractableElement {

    WaterModel(Position pos) {
        super(pos);
    }

    @Override
    public void interact(PlayerModel player) throws NourishRestored {
        player.getWater().increaseValue(20);
    }
}
