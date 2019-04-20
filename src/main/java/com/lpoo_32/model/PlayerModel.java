package com.lpoo_32.model;


import java.util.LinkedList;
import java.util.List;

public class PlayerModel extends MovableElement {
    private Status health;
    private Status food;
    private Status water;
    private Inventory inventory;

    public PlayerModel(Position position){
        super(position);
        this.health = new Status(100);
        this.food = new Status(100);
        this.water = new Status(100);
        this.inventory =  new Inventory();
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

    public void addElementInventory(InteractableElement element)
    {
        inventory.addElement(element);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
