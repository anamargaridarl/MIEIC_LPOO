package com.lpoo_32.model;

public class FoodModel extends InteractableElement {
    private int value;

    public FoodModel(int value, Position pos){
        super(pos);
        this.value = value;
    }
    @Override
    public void interact(PlayerModel player) {
        //TODO: Add some percentage value to it?
        player.getHealth().increaseValue(this.value);
        player.getFood().increaseValue(this.value);
        player.getWater().increaseValue(this.value);
    }
}
