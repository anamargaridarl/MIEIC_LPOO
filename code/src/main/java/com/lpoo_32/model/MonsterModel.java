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
    private Position playerposition;
    int number;
    GameController controller;

    public MonsterModel(Position pos, int value, MovableElement movable, GameController controller, Position playerposition) {
        super(pos, value);
        this.movable = movable;
        this.number = 0;
        this.controller= controller;
        this.playerposition = playerposition;
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
                    if (!(controller.checkElement(monsterview.getMonster().getPos().checkMovementUp()))) {
                        controller.removeElementProps(monsterview.getMonster());
                        monsterview.getMonster().moveUp();
                        controller.addElementProps(monsterview);
                    }

                    break;
            case DOWN:
                if(!(controller.checkElement(monsterview.getMonster().getPos().checkMovementDown()))) {
                    controller.removeElementProps(monsterview.getMonster());
                    monsterview.getMonster().moveDown();
                    controller.addElementProps(monsterview);
                }
                break;
            case LEFT:
                if (!(controller.checkElement(monsterview.getMonster().getPos().checkMovementLeft()))) {
                    controller.removeElementProps(monsterview.getMonster());
                    monsterview.getMonster().moveLeft();
                    controller.addElementProps(monsterview);
                }
                break;
            case RIGHT:
                if (!(controller.checkElement(monsterview.getMonster().getPos().checkMovementRight()))) {
                    controller.removeElementProps(monsterview.getMonster());
                    monsterview.getMonster().moveRight();
                    controller.addElementProps(monsterview);
                }
                break;
            default:
                break;
        }


    }


    public void decreaseValue(int value) {
        if(this.getValue() - value < 0)
            this.value = 100;
        else
            this.value -= value;
    }


    public boolean equalsPlayer(Position player) {

        if (Math.abs(this.movable.getPosition().getX() - player.getX()) == 0 && Math.abs(this.movable.getPosition().getY() - player.getY()) == 1
        || Math.abs(this.movable.getPosition().getX() - player.getX()) == 1 && Math.abs(this.movable.getPosition().getY() - player.getY()) == 0) {
            System.out.println("bananas");
            return true;
        } else {
            return false;
        }
    }


    public int chooseX(Position playerposition) {
        if (this.movable.getPosition().getX() < playerposition.getX())
            return 3;
        else if (this.movable.getPosition().getX() > playerposition.getX())
            return 2;
        else
            return chooseY(playerposition);
    }

    private int chooseY( Position playerposition) {
        if (this.movable.getPosition().getY() < playerposition.getY())
            return 1;
        else if (this.movable.getPosition().getY() > playerposition.getY())
            return 0;
        else
            return chooseX(playerposition);
    }

    //chose between moving in y or x
    public int randomMove(Position playerposition) throws LeftScreen, RightScreen, UpScreen, DownScreen {
        Random random = new Random();
        int mov = random.nextInt((2 - 2) + 1) + 1;

        if (mov == 1) {
            return chooseX(playerposition);
        } else {
           return chooseY(playerposition);
        }

    }


    public void moveMonster(MonsterView monsterview, Position playerposition) throws RightScreen, LeftScreen, UpScreen, DownScreen {

        if(equalsPlayer(playerposition)) {
            return;
        }

        Movements[] mov = Movements.values();
        int n = randomMove(playerposition);

        this.getMovement(mov[n],monsterview);

    }
    public void updateMove(MonsterView monsterview) throws HungerRestored, ThirstOVF, HealthOVF, HungerOVF, ThirstRestored, UpScreen, LeftScreen, RightScreen, DownScreen {

        this.number++;

        if (number == 60) {
            if(this.getPos().getIndex() == playerposition.getIndex()) {
                controller.checkCollisionMonster(movable.getPosition());
                this.moveMonster(monsterview,playerposition);
                number = 0;
            }
        }
    }




}
