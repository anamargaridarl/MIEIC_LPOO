package com.lpoo_32.model;


public class WaterModel extends CatchableElement {

    WaterModel(Position pos) {
        super(pos);
    }

    @Override
    public void interact(PlayerModel player) {
        player.getWater().increaseValue(20);
    }
}
