package com.lpoo_32.model;

public abstract class ValuableElement extends InteractableElement{
    private int value;

    ValuableElement(Position pos, int value) {
        super(pos);
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
