package com.lpoo_32.model;

import java.util.LinkedList;
import java.util.List;

public class Inventory {

    private List<InteractableElement> inventory;
    private int index;

    public Inventory(){
        this.inventory = new LinkedList<>();
        index = 0;
    }

    public List<InteractableElement> getInventory() {
        return inventory;
    }

    public void setInventory(List<InteractableElement> inventory) {
        this.inventory = inventory;
    }

    public void addElement(InteractableElement element) {
        inventory.add(element);
    }

    public void moveRight()
    {
        if(this.index + 1 < inventory.size() -1 )
            this.index++;

    }

    public void moveLeft()
    {
        if(this.index - 1 > 0)
            this.index--;

    }

    public void removeElement() {
        if(index < 0) {
            return;
        }

        inventory.remove(index);
        index--;
    }

    public InteractableElement getElement() {
        if(index < 0) {
            return null;
        }
        return inventory.get(index);
    }


}
