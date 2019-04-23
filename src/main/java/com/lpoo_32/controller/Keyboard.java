package com.lpoo_32.controller;

import java.io.IOException;
import java.util.List;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;
import com.lpoo_32.model.SpikesModel;
import com.lpoo_32.exceptions.ScreenClose;
import com.lpoo_32.model.*;
import com.lpoo_32.view.CatchableView;
import com.lpoo_32.view.ElementView;
import com.lpoo_32.view.FoodView;

public class Keyboard
{
    private PlayerModel player;
    private Elements elements;
    private List<ElementView> props;

    public Keyboard(PlayerModel player, Elements elements,List<ElementView> props)
    {
        this.player = player;
        this.elements = elements;
        this.props = props;
    }

    public void processKey(Screen screen) throws IOException, ScreenClose, HealthOVF, HungerRestored, HungerOVF, ThirstRestored, ThirstOVF {

        KeyStroke key;
        key = screen.pollInput();
        SpikesModel spikes = new SpikesModel(10, null);
        if(key != null){
            switch (key.getKeyType()) {
                case ArrowUp:
                    player.moveUp();
                    break;
                case ArrowDown:
                    player.moveDown();
                    break;
                case ArrowLeft:
                    player.moveLeft();
                    break;
                case ArrowRight:
                    player.moveRight();
                    break;
                case Character:
                    switch (key.getCharacter()){
                        case 'z':
                            throw new ScreenClose();
                        case 'p':
                            spikes.interact(player);
                            break;
                        case 'h':
                            //TODO: How to proceed with this?
                            System.out.println("Open help Menu");
                            break;
                        case 'f': //add element to inventory
                            if(isCatchable(player.getPosition())) {
                                player.addElementInventory((CatchableElement)elements.getElement(player.getPosition()));
                                removeElementProps(elements.getElement(player.getPosition()));
                            }
                            break;
                        case 't': //use water/food in moment
                            if(isCatchable(player.getPosition())) {
                                elements.getElement(player.getPosition()).interact(player);
                                removeElementProps(elements.getElement(player.getPosition()));
                            }
                            break;
                        case 'q': //move left in inventory
                            player.getInventory().moveLeft();
                            break;
                        case 'w': //move right in inventory
                            player.getInventory().moveRight();
                            break;
                        case 'e':
                            if(player.getInventory().getElement() != null)
                                player.getInventory().getElement().interact(player);
                            player.getInventory().removeElement();
                            break;
                        default:
                            break;


                    }
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

        if (elements.getElement(position) != null)
            return elements.getElement(position) instanceof CatchableElement;
        else
            return false;

    }


    //handles colisions for non catchable elements
    public void collisions(Position position) throws HungerRestored, HungerOVF, ThirstRestored, ThirstOVF { //TODO: Mo {

        try {
            if (elements.getElement(position) != null && !(elements.getElement(position) instanceof CatchableElement)) {
                    elements.getElement(position).interact(player);

            }
        } catch (HealthOVF e) {
            System.out.printf("Status Overflow");
            System.exit(0);
        }


    }

}
