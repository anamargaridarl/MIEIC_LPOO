package com.lpoo_32.model;

import java.util.LinkedHashMap;

public class Elements {

    private LinkedHashMap<Position,InteractableElement> elements;

    public Elements() {
        this.elements = new LinkedHashMap<>();
    }

    public void addElement(InteractableElement a) //TODO:verify if elements are added in same position
    {
        elements.put(a.getPos(),a);
    }

    public LinkedHashMap<Position,InteractableElement> getElements()
    {
        return elements;
    }

    public InteractableElement getElement(Position position)
    {
        return elements.get(position);
    }

}
