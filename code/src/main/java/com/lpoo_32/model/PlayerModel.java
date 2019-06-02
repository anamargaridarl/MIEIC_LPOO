package com.lpoo_32.model;

import com.lpoo_32.view.CatchableView;

public class PlayerModel extends MovableElement {
    private Status health;
    private Status food;
    private Status water;
    private WeaponModel currentWeapon;
    private final Inventory inventory;

    public PlayerModel(Position position){
        super(position, "stickman.png");
        this.inventory =  new Inventory();
        this.health = new HealthStatus(100);
        this.food = new NourishStatus(100, NourishType.HUNGER);
        this.water = new NourishStatus(100, NourishType.THIRST);
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

    public void addElementInventory(CatchableView view)
    {
        inventory.addElement(view);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setWeapon(WeaponModel weapon) {
        this.currentWeapon = weapon;
    }

    public WeaponModel getWeapon() {
        return this.currentWeapon;
    }
}
