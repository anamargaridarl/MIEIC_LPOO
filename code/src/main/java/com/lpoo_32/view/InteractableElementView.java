package com.lpoo_32.view;

import com.lpoo_32.model.InteractableElement;

public abstract class InteractableElementView implements ElementView{
    private InteractableElement element;

    public InteractableElementView(InteractableElement element){
        this.element = element;
    }

    public InteractableElement getElement() {
        return element;
    }


}
