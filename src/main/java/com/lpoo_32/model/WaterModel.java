package com.lpoo_32.model;


public class WaterModel extends InteractableElement {

    WaterModel(Position pos) {
        super(pos);
    }

    @Override
    void interact(PlayerModel player) {
        player.getWater().increaseValue(20);
    }
}
