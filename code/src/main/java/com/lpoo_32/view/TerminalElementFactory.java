package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.lpoo_32.model.*;

public class TerminalElementFactory implements ElementFactory {
    @Override
    public InteractableElementView getElement(ElementType element, Position pos) {
        InteractableElementView interactableElement;
        switch (element){
            case SPIKES:
                interactableElement = new SpikesView(new SpikesModel(30, pos));
                break;
            case WATER:
                interactableElement = new WaterView(new WaterModel(30, pos));
                break;
            case FOOD:
                interactableElement = new FoodView(new FoodModel(30, pos));
                break;
            case MONSTER:
                interactableElement = new MonsterView(new MonsterModel(pos, 30, new MovableElement(pos)));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + element);
        }
        return interactableElement;
    }
}
