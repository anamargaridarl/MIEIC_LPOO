package com.lpoo_32.view.lanterna;

import com.lpoo_32.model.FoodModel;
import com.lpoo_32.model.Position;
import com.lpoo_32.model.SpikesModel;
import com.lpoo_32.model.WaterModel;
import com.lpoo_32.view.ElementFactory;
import com.lpoo_32.view.ElementType;
import com.lpoo_32.view.InteractableElementView;

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
            default:
                throw new IllegalStateException("Unexpected value: " + element);
        }
        return interactableElement;
    }
}
