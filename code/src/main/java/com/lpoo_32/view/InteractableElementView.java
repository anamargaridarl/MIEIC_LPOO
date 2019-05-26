package com.lpoo_32.view;

import com.lpoo_32.model.InteractableElement;

public abstract class InteractableElementView extends ElementView{
    private InteractableElement element;

    public InteractableElementView(InteractableElement element, String name){
        super(name);
        this.element = element;
    }

    public InteractableElement getElement() {
        return element;
    }


}
