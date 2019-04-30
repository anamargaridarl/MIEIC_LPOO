package com.lpoo_32.controller;

import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.*;
import com.lpoo_32.view.CatchableView;
import com.lpoo_32.view.ElementView;
import com.lpoo_32.view.EventType;

import java.util.List;

public class GameController
{
    private PlayerModel player;
    private Elements elements;

    public GameController(PlayerModel player, Elements elements)
    {
        this.player = player;
        this.elements = elements;
    }

    public void processKey(EventType event) throws ScreenClose, HealthOVF, HungerRestored, HungerOVF, ThirstRestored, ThirstOVF, UpScreen, LeftScreen, RightScreen, DownScreen {

        SpikesModel spikes = new SpikesModel(10, null);
        if(event != EventType.NULL && event != null){
            switch (event) {
                case MOVEUP:
                    player.moveUp();
                    break;
                case MOVEDOWN:
                    player.moveDown();
                    break;
                case MOVELEFT:
                    player.moveLeft();
                    break;
                case MOVERIGHT:
                    player.moveRight();
                    break;
                case EXIT:
                    throw new ScreenClose();
                case NULL:
                    spikes.interact(player);
                    break;
                case HELP:
                    //TODO: How to proceed with this?
                    System.out.println("Open help Menu");
                    break;
                case STORE: //add element to inventory
                    if(isCatchable(player.getPosition())) {
                        player.addElementInventory((CatchableElement)elements.getModel(player.getPosition()));
                        removeElementProps(elements.getModel(player.getPosition()));
                    }
                    break;
                case USE: //use water/food in moment
                    if(isCatchable(player.getPosition())) {
                        elements.getModel(player.getPosition()).interact(player);
                        removeElementProps(elements.getModel(player.getPosition()));
                    }
                    break;
                case LEFTINVENTORY: //move left in inventory
                    player.getInventory().moveLeft();
                    break;
                case RIGHTINVENTORY: //move right in inventory
                    player.getInventory().moveRight();
                    break;
                case INVETORYUSE:
                    if(player.getInventory().getElement() != null)
                        player.getInventory().getElement().interact(player);
                    player.getInventory().removeElement();
                    break;
            }

            this.collisions(player.getPosition());
        }
    }


    //remove element from view array
    public void removeElementProps(InteractableElement element) {
        this.elements.removeElement(element);
    }

    //verify that model element in the position is catchable
    private boolean isCatchable(Position position) {

        if (elements.getView(position) != null)
            return elements.getModel(position) instanceof CatchableElement;
        else
            return false;

    }


    //handles colisions for non catchable elements
    public void collisions(Position position) throws HungerRestored, HungerOVF, ThirstRestored, ThirstOVF, HealthOVF { //TODO: Mo {

        if (elements.getView(position) != null && !(isCatchable(position))) {
            elements.getModel(position).interact(player);
        }
    }
}
