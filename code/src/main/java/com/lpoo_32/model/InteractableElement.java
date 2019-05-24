package com.lpoo_32.model;


import com.lpoo_32.exceptions.*;

abstract public class InteractableElement implements ElementModel {

    private Position pos;
    protected int value;

    InteractableElement(Position pos, int value){
        this.pos = pos;
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public abstract void interact(PlayerModel player) throws HealthOVF, HungerRestored, HungerOVF, ThirstRestored, ThirstOVF;

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


}
