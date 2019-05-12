package com.lpoo_32.model;


import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.view.ElementType;
import com.lpoo_32.view.MonsterView;

import java.util.Random;

enum Movements{
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class MonsterModel extends InteractableElement {

    private MovableElement movable;
    int number;
    GameController controller;

    public MonsterModel(Position pos, int value, MovableElement movable, GameController controller) {
        super(pos, value);
        this.movable = movable;
        this.number = 0;
        this.controller= controller;
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

    public void getMovement(Movements mov, MonsterView monsterview) throws UpScreen, DownScreen, LeftScreen, RightScreen {

        switch(mov)
        {
            case UP:
                this.moveUp();
            case DOWN:
                this.moveDown();
            case LEFT:
                this.moveLeft();
            case RIGHT:
                this.moveRight();
        }

        controller.removeElementProps(this);

        try {
            controller.addElementProps(monsterview);
        }
        catch(OccupiedElement e)
        {
            switch(mov)
            {
                case UP:
                    this.moveDown();
                case DOWN:
                    this.moveUp();
                case LEFT:
                    this.moveRight();
                case RIGHT:
                    this.moveLeft();
            }

        }


    }


    public void moveMonster(MonsterView monsterview) throws RightScreen, LeftScreen, UpScreen, DownScreen {
        Random random = new Random();
        Movements[] mov = Movements.values();
        this.getMovement(mov[random.nextInt(mov.length - 1)], monsterview);

    }
    public void updateMove(MonsterView monsterview) throws HungerRestored, ThirstOVF, HealthOVF, HungerOVF, ThirstRestored, UpScreen, LeftScreen, RightScreen, DownScreen {

        this.number++;

        if (number == 60) {
            controller.checkCollisionMonster(movable.getPosition());
            this.moveMonster(monsterview);
            number = 0;
        }
    }




}
