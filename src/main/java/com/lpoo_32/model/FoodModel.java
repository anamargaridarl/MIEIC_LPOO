package com.lpoo_32.model;

public class FoodModel extends CatchableElement {
    private int value;

    public FoodModel(int value, Position pos){
        super(pos);
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
    @Override
    public void interact(PlayerModel player) {
        //TODO: Add some percentage value to it?
        player.getHealth().increaseValue(this.value);
        player.getFood().increaseValue(this.value);
        player.getWater().increaseValue(this.value);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        FoodModel p = (FoodModel) o;
        return value == p.getValue() && getPos() == p.getPos();
    }
}
