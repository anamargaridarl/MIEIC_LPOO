package com.lpoo_32.model;


import com.lpoo_32.exceptions.*;

public class MonsterModel extends InteractableElement {

    private MovableElement movable;

    public MonsterModel(Position pos, int value, MovableElement movable) {
        super(pos, value);
        this.movable = movable;
    }

    @Override
    public void interact(PlayerModel player) throws HungerOVF, HealthOVF, ThirstOVF {
        player.getHealth().decreaseValue(this.getValue());
    }

    public void moveUp() throws UpScreen {
        movable.moveUp();
    }

    public void moveDown() throws DownScreen {
        movable.moveDown();
    }

    public void moveLeft() throws LeftScreen {
        movable.moveLeft();
    }

    public void moveRight() throws RightScreen {
        movable.moveRight();
    }




}
