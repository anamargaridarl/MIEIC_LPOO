package com.lpoo_32.model;

import com.lpoo_32.view.ElementView;

import java.util.ArrayList;
import java.util.List;

public class Elements {

    private List<ElementView> elements;

    public Elements() {
        this.elements = new ArrayList<>();
    }

    public void addElement(ElementView a)
    {
        elements.add(a);
    }

    public List<ElementView> getElements()
    {
        return elements;
    }

}
