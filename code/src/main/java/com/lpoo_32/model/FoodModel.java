package com.lpoo_32.model;

import com.lpoo_32.exceptions.HungerRestored;
import com.lpoo_32.exceptions.ThirstRestored;

public class FoodModel extends CatchableElement {

    public FoodModel(int value, Position pos){
        super(pos, value, "drumstick.png");
    }


    @Override
    public boolean interact(PlayerModel player) throws HungerRestored, ThirstRestored {
        //TODO: Add some percentage value to it?
        player.getHealth().increaseValue(this.getValue());
        player.getFood().increaseValue(this.getValue());
        player.getWater().increaseValue(this.getValue());
        return true;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        FoodModel p = (FoodModel) o;
        return getValue() == p.getValue() && getPos() == p.getPos();
    }

}
