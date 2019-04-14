package com.lpoo_32.model;

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

    public void setHealth(Status health) {
        this.health = health;
    }

    public Status getFood() {
        return food;
    }

    public void setFood(Status food) {
        this.food = food;
    }

    public Status getWater() {
        return water;
    }

    public void setWater(Status water) {
        this.water = water;
    }
}
