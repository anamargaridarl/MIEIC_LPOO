package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.NourishOVF;
import com.lpoo_32.exceptions.NourishRestored;

abstract public class InteractableElement implements ElementModel {

    private Position pos;

    InteractableElement(Position pos){
        this.pos = pos;
    }

    public abstract void interact(PlayerModel player) throws HealthOVF, NourishRestored, NourishOVF;

    public Position getPos() {
        return pos;
    }


}
