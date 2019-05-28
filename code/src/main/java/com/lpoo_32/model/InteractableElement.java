package com.lpoo_32.model;


import com.lpoo_32.exceptions.*;

abstract public class InteractableElement extends ElementModel {

    private Position pos;
    protected int value;

    InteractableElement(Position pos, int value, String name){
        super(name);
        this.pos = pos;
        this.value = value;
    }

    public abstract boolean interact(PlayerModel player) throws HealthOVF, HungerRestored, HungerOVF, ThirstRestored, ThirstOVF, Bedtime;

    public Position getPos() {
        return pos;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        InteractableElement p = (InteractableElement) o;
        return getPos() == p.getPos();
    }

    public void setPosition(Position player)
    {
        this.pos = player;
    }


    public int getValue() {
        return this.value;
    }
}
