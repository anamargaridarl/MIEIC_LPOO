package com.lpoo_32.model;


import com.lpoo_32.exceptions.*;

abstract public class InteractableElement implements ElementModel {

    private Position pos;

    InteractableElement(Position pos){
        this.pos = pos;
    }

    public abstract boolean interact(PlayerModel player) throws HealthOVF, HungerRestored, HungerOVF, ThirstRestored, ThirstOVF;

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
}
