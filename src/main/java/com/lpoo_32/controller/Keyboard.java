package com.lpoo_32.controller;

import java.io.IOException;
import java.util.List;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.ScreenClose;
import com.lpoo_32.exceptions.StatusOverflow;
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

    public void processKey(Screen screen) throws IOException, ScreenClose, StatusOverflow {

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
                                player.addElementInventory(elements.getValue(player.getPosition()));
                                removeElementProps(elements.getValue(player.getPosition()));
                            }
                            break;
                        case 't': //use water/food in moment
                            if(isCatchable(player.getPosition())) {
                                elements.getValue(player.getPosition()).interact(player);
                                removeElementProps(elements.getValue(player.getPosition()));
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

        if (elements.getValue(position) != null)
            return elements.getValue(position) instanceof CatchableElement;
        else
            return false;

    }


    //handles colisions for non catchable elements
    public void collisions(Position position) {

        try {
            if (elements.getValue(position) != null && !(elements.getValue(position) instanceof CatchableElement)) {
                    elements.getValue(position).interact(player);

            }
        } catch (StatusOverflow e) {
            System.out.printf("Status Overflow");
            System.exit(0);
        }


    }

}
