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

    private int health;
    private MovableElement movable;
    private Position playerposition;
    int number;
    GameController controller;

    public MonsterModel(Position pos, int value, MovableElement movable, GameController controller, Position playerposition) {
        super(pos, value, "monster.png");
        this.movable = movable;
        this.number = 0;
        this.controller= controller;
        this.playerposition = playerposition;
        this.health = 50;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }
    @Override
    public boolean interact(PlayerModel player) throws HungerOVF, HealthOVF, ThirstOVF {
        player.getHealth().decreaseValue(this.getValue());
        return false;
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

    public boolean getMovement(Movements mov, MonsterView monsterview) throws UpScreen, DownScreen, LeftScreen, RightScreen {

        switch(mov)
        {
            case UP:
                    if (!(controller.checkForElement(monsterview.getMonster().getPos().checkMovementUp()))) {
                        controller.removeElementProps(monsterview.getMonster());
                        monsterview.getMonster().moveUp();
                        controller.addElementProps(monsterview);
                        return true;
                    }
                    break;
            case DOWN:
                if(!(controller.checkForElement(monsterview.getMonster().getPos().checkMovementDown()))) {
                    controller.removeElementProps(monsterview.getMonster());
                    monsterview.getMonster().moveDown();
                    controller.addElementProps(monsterview);
                    return true;
                }
                break;
            case LEFT:
                if (!(controller.checkForElement(monsterview.getMonster().getPos().checkMovementLeft()))) {
                    controller.removeElementProps(monsterview.getMonster());
                    monsterview.getMonster().moveLeft();
                    controller.addElementProps(monsterview);
                    return true;
                }
                break;
            case RIGHT:
                if (!(controller.checkForElement
                        (monsterview.getMonster().getPos().checkMovementRight()))) {
                    controller.removeElementProps(monsterview.getMonster());
                    monsterview.getMonster().moveRight();
                    controller.addElementProps(monsterview);
                    return true;
                }
                break;
            default:
                break;
        }

return false;

    }


    public void decreaseValue(int value)  {
        if(this.health - value <= 0) {
            this.controller.removeElementProps(this);

        }
        else
            this.health -= value;
    }


    //-----------------Choose to move in x or y------------------------------
    public int chooseX(Position playerposition) {
        if (this.getPos().getX() < playerposition.getX())
            return 3;
        else if (this.getPos().getX() > playerposition.getX())
            return 2;
        else if (this.getPos().getX() == playerposition.getX()) {
            return chooseY(playerposition);
        }

        return 0;
    }

    int chooseY(Position playerposition) {
        if (this.getPos().getY() < playerposition.getY())
            return 1;
        else if (this.getPos().getY() > playerposition.getY())
            return 0;
        else if (this.getPos().getY() == playerposition.getY()) {
            return chooseX(playerposition);
        }

        return 0;
    }

    //chose between moving in y or x
    public int randomMove(Position playerposition) throws LeftScreen, RightScreen, UpScreen, DownScreen {
        Random random = new Random();

        int mov = random.nextInt(2);

        if (mov == 1) {
            return chooseX(playerposition);
        } else {
           return chooseY(playerposition);
        }

    }
    //-----------------------------------------------------------------------------

    public void moveMonster(MonsterView monsterview, Position playerposition) throws RightScreen, LeftScreen, UpScreen, DownScreen {

            if (controller.monsterEqualsPlayer(playerposition, this.getPos())) {
                return;
            }

            Movements[] mov = Movements.values();
            int n = randomMove(playerposition);

           this.getMovement(mov[n], monsterview);
        }


    public void updateMove(MonsterView monsterview) throws HungerRestored, ThirstOVF, HealthOVF, HungerOVF, ThirstRestored, UpScreen, LeftScreen, RightScreen, DownScreen, Bedtime {

        this.number++;

        if (number == 15) {


            if(this.getPos().getIndex() == playerposition.getIndex()) {
                controller.checkCollisionMonster(movable.getPosition());
                this.moveMonster(monsterview,playerposition);
                number = 0;
            }
        }
    }




}
