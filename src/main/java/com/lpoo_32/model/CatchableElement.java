package com.lpoo_32.model;

abstract public class CatchableElement extends InteractableElement  {

    abstract public char getSymbol();
    abstract public String getName();

    CatchableElement(Position pos, int value) {
        super(pos, value);
    }


}
