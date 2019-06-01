package com.lpoo_32.view;

import com.lpoo_32.model.*;

public class ElementFactory {
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
            case WALL:
                interactableElement = new WallView(new WallModel(pos));
                break;
            case DOOR:
                interactableElement = new DoorView(new DoorModel(pos));
                break;
            case BED:
                interactableElement = new BedView(new BedModel(pos));
                break;
            case NONE:
            default:
                throw new IllegalStateException("Unexpected value: " + element);
        }
        return interactableElement;
    }
}
