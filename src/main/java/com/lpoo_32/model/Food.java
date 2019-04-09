package com.lpoo_32.model;

public class Food implements InteractableElement {
    private int value;

    public Food(int value){
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
