package com.lpoo_32.model;


import com.lpoo_32.exceptions.*;

public class WaterModel extends CatchableElement {

    public WaterModel(int value, Position pos) {
        super(pos, value, "water.png");
    }

    @Override
    public boolean interact(PlayerModel player) throws HungerRestored, ThirstRestored {
        player.getWater().increaseValue(this.getValue());
        player.getHealth().increaseValue((int) (this.getValue()*0.2));
        return true;
    }


}
