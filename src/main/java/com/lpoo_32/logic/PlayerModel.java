package com.lpoo_32.logic;

public class PlayerModel {
    private Status health;
    private Status food;
    private Status water;

    PlayerModel(){
        this.health = new Status(100);
        this.food = new Status(100);
        this.water = new Status(100);
    }

    public Status getHealth() {
        return health;
    }

    public Status getFood() {
        return food;
    }

    public Status getWater() {
        return water;
    }
}
