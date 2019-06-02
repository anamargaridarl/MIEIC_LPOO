package com.lpoo_32.view;

import com.lpoo_32.model.InteractableElement;

public abstract class InteractableElementView extends ElementView{
    private final InteractableElement element;

    InteractableElementView(InteractableElement element){
        super(element);
        this.element = element;
    }

    public InteractableElement getElement() {
        return element;
    }


}
