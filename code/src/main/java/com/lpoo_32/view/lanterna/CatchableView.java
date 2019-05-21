package com.lpoo_32.view.lanterna;

import com.googlecode.lanterna.Symbols;
import com.lpoo_32.model.CatchableElement;
import com.lpoo_32.model.InteractableElement;
import com.lpoo_32.view.InteractableElementView;

abstract public class CatchableView extends InteractableElementView {

    public CatchableView(InteractableElement element) {
        super(element);
    }

    abstract public char getSymbol();
    abstract public String getName();

    abstract public CatchableElement getElement();
}
