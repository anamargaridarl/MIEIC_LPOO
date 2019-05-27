package com.lpoo_32.model;

import com.lpoo_32.exceptions.DownScreen;
import com.lpoo_32.exceptions.LeftScreen;
import com.lpoo_32.exceptions.RightScreen;
import com.lpoo_32.exceptions.UpScreen;

public class MovableElement extends ElementModel {

    private Position position;

    public MovableElement(Position position, String name){
        super(name);
        this.position = position;
    }

    public void moveUp() throws UpScreen {
        position.moveUp();
    }

    public void moveDown() throws DownScreen {
        position.moveDown();
    }

    public void moveLeft() throws LeftScreen {
        position.moveLeft();
    }

    public void moveRight() throws RightScreen {
        position.moveRight();
    }

    public Position getPosition()
    {
        return position;
    }


}
