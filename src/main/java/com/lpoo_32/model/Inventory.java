package com.lpoo_32.model;

import com.lpoo_32.view.CatchableView;

import java.util.LinkedList;
import java.util.List;

public class Inventory {

    private List<CatchableView> inventory;
    private int index;

    public Inventory(){
        this.inventory = new LinkedList<>();
        index = 0;
    }

    public void addElement(CatchableView view) {
        inventory.add(view);
    }

    public void moveRight()
    {
        if(this.index + 1 <= inventory.size() -1 )
            this.index++;

    }

    public void moveLeft()
    {
        if(this.index - 1 >= 0)
            this.index--;

    }

    public void updateRemoveIndex()
    {
        if(index > 0)
            index--;
    }

    public void removeElement() {
        if(index < 0) {
            return;
        }

        inventory.remove(index);
        this.updateRemoveIndex();
    }

    public CatchableElement getElement() {
        if(index < 0 || inventory.size() == 0) {
            return null;
        }
        return inventory.get(index).getElement();
    }

    public CatchableView getView() {
        if(index < 0 || inventory.size() == 0) {
            return null;
        }
        return inventory.get(index);
    }


}
