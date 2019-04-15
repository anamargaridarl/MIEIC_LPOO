package com.lpoo_32.model;

abstract public class MovableElement implements ElementModel {

    private Position position;

    MovableElement(Position position){
        this.position = position;
    }

    public void moveUp() {
        position.moveUp();
    }

    public void moveDown() {
        position.moveDown();
    }

    public void moveLeft() {
        position.moveLeft();
    }

    public void moveRight() {
        position.moveRight();
    }

    public Position getPosition()
    {
        return position;
    }


}
