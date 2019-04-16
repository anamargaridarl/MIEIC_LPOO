package com.lpoo_32.model;

import com.lpoo_32.exceptions.StatusOverflow;

abstract class InteractableElement implements ElementModel {

    private Position pos;

    InteractableElement(Position pos){
        this.pos = pos;
    }
    abstract void interact(PlayerModel player) throws StatusOverflow;

    public Position getPos() {
        return pos;
    }
}
