package com.lpoo_32.model;

import com.lpoo_32.view.InteractableElementView;

import java.util.ArrayList;
import java.util.List;

public class Elements {

    private List<List<InteractableElementView>> elements;

    public Elements() {
        this.elements = new ArrayList<>();
        for(int i = 0; i <= 45; i++){
            elements.add(new ArrayList<>());
            for(int j = 0; j <= 36; j++){
                elements.get(i).add(null);
            }
        }
    }

    public boolean addElement(InteractableElementView a)
    {
        if(elements.get(a.getElement().getPos().getX()).get(a.getElement().getPos().getY()) == null)
        {
            elements.get(a.getElement().getPos().getX()).set(a.getElement().getPos().getY(), a);
            return true;
        }
        else
            return false;
    }

    public void forceAddElement(InteractableElementView a){
        elements.get(a.getElement().getPos().getX()).set(a.getElement().getPos().getY(), a);
    }

    public InteractableElementView getView(Position position)
    {
        return getViewByCoord(position.getX(), position.getY());
    }

    public InteractableElement getModel(Position position){
        return getModelByCoord(position.getX(), position.getY());
    }


    public InteractableElementView getViewByCoord(int x, int y){
        return elements.get(x).get(y);
    }

    public InteractableElement getModelByCoord(int x, int y){
        return elements.get(x).get(y).getElement();
    }

    public void removeElement(InteractableElement element){
        this.elements.get(element.getPos().getX()).set(element.getPos().getY(), null);
    }
}
