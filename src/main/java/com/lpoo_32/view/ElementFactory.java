package com.lpoo_32.view;

import com.lpoo_32.model.Position;

enum ElementType {
    SPIKES,
    WATER,
    FOOD
}

public interface ElementFactory {
    InteractableElementView getElement(ElementType element, Position pos);
}
