package com.lpoo_32.controller;

import java.io.IOException;
import java.util.List;

import com.googlecode.lanterna.input.KeyStroke;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;
import com.lpoo_32.model.SpikesModel;
import com.lpoo_32.exceptions.ScreenClose;
import com.lpoo_32.model.*;
import com.lpoo_32.view.*;

public class GameController
{
    private PlayerModel player;
    private Elements elements;
    private List<ElementView> props;

    public GameController(PlayerModel player, Elements elements, List<ElementView> props)
    {
        this.player = player;
        this.elements = elements;
        this.props = props;
    }

    public void processKey(EventType event) throws IOException, ScreenClose, HealthOVF, HungerRestored, HungerOVF, ThirstRestored, ThirstOVF, UpScreen, LeftScreen, RightScreen, DownScreen {

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
                        player.addElementInventory((CatchableElement)elements.getValue(player.getPosition()));
                        removeElementProps(elements.getValue(player.getPosition()));
                    }
                    break;
                case USE: //use water/food in moment
                    if(isCatchable(player.getPosition())) {
                        elements.getValue(player.getPosition()).interact(player);
                        removeElementProps(elements.getValue(player.getPosition()));
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
        for (ElementView e : this.props) //need to divide health related elements in a subclass
        {
            if (e instanceof CatchableView) {
                if (((CatchableView) e).getElement().equals(element)) {
                    props.remove(e);
                    break;
                }
            }

        }
    }

    //verify that model element in the position is catchable
    public boolean isCatchable(Position position) {

        if (elements.getValue(position) != null)
            return elements.getValue(position) instanceof CatchableElement;
        else
            return false;

    }


    //handles colisions for non catchable elements
    public void collisions(Position position) throws HungerRestored, HungerOVF, ThirstRestored, ThirstOVF, HealthOVF { //TODO: Mo {

        if (elements.getValue(position) != null && !(isCatchable(position))) {
            elements.getValue(position).interact(player);
        }
    }
}
