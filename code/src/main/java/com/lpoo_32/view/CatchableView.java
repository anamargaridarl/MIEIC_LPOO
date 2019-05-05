package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.lpoo_32.model.CatchableElement;
import com.lpoo_32.model.InteractableElement;

abstract public class CatchableView extends InteractableElementView{

    public CatchableView(InteractableElement element) {
        super(element);
    }

    abstract public char getSymbol();
    abstract public String getName();

    abstract public CatchableElement getElement();
}
