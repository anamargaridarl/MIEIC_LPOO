package com.lpoo_32.controller;

import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.*;
import com.lpoo_32.view.*;
import com.lpoo_32.view.lanterna.DisplayProps;
import com.lpoo_32.view.lanterna.*;

import java.io.IOException;
import java.util.Random;

public class GameController
{
    private final GameLanterna gameLanterna;
    private PlayerModel player;
    private Elements elements;
    private TerminalKeyboard keyboardProcessor;
    private boolean hunger;
    private boolean thirst;
    private static final int frameRate = 60;
    private int time;


    public GameController(DisplayProps props, Elements elements, PlayerModel player) throws OutOfBoundaries {
        this.player = player;
        this.elements = elements;
        this.keyboardProcessor = new TerminalKeyboard(props.getScreen());
        this.hunger = false;
        this.thirst = false;
        this.populateGame(GameLanterna.width/4, GameLanterna.height/4);
        this.gameLanterna = new GameLanterna(props, this.player, elements);
    }

    void processKey(EventType event) throws ScreenClose, HealthOVF, HungerRestored, HungerOVF, ThirstRestored, ThirstOVF, UpScreen, LeftScreen, RightScreen, DownScreen {

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
                        player.addElementInventory((CatchableView)elements.getView(player.getPosition()));
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
                    if(player.getInventory().getElement() != null) {
                        player.getInventory().getElement().interact(player);
                        player.getInventory().removeElement();
                    }
                    break;
            }

            this.collisions(player.getPosition());
        }
    }

    void updateGame() throws IOException, ScreenClose, HealthOVF, InterruptedException, RightScreen, LeftScreen, UpScreen, DownScreen {
        try {
            this.processKey(this.keyboardProcessor.processKey());
            this.gameLanterna.draw();
            Thread.sleep(1000/ frameRate);
            updateNourishment();
        } catch (HungerRestored hungerRestored) {
            this.hunger = false;
        } catch (HungerOVF nourishOVF) {
            this.hunger = true;
        } catch (ThirstRestored thirstRestored) {
            this.thirst = false;
        } catch (ThirstOVF thirstOVF) {
            this.thirst = true;
        } catch (RightScreen rightScreen) {
            if(this.gameLanterna.getIndex()%3  != 2){
                this.gameLanterna.setIndex(this.gameLanterna.getIndex() + 1);
                this.player.getPosition().setIndex(this.gameLanterna.getIndex());
            }
        } catch (LeftScreen leftScreen) {
            if(this.gameLanterna.getIndex()%3  != 0){
                this.gameLanterna.setIndex(this.gameLanterna.getIndex() - 1);
                this.player.getPosition().setIndex(this.gameLanterna.getIndex());
            }
        } catch (UpScreen upScreen) {
            if(this.gameLanterna.getIndex() - 3  >= 0){
                this.gameLanterna.setIndex(this.gameLanterna.getIndex() - 3);
                this.player.getPosition().setIndex(this.gameLanterna.getIndex());
            }
        } catch (DownScreen downScreen) {
            if(this.gameLanterna.getIndex() + 3  < 9){
                this.gameLanterna.setIndex(this.gameLanterna.getIndex() + 3);
                this.player.getPosition().setIndex(this.gameLanterna.getIndex());
            }
        }
    }

    private void updateNourishment() throws HungerOVF, ThirstOVF, HealthOVF {
        time++;
        if(time % (3600) == 0) {
            this.player.getWater().decreaseValue(5);
        }

        if(time % (5400.0) == 0) {
            this.player.getFood().decreaseValue(5);
        }
        if((this.hunger || this.thirst) && ((time % 120) == 0))
            this.player.getHealth().decreaseValue(5);
    }

    public void run() throws IOException {
        this.gameLanterna.draw();

        //isto parece shady; ter um ciclo infinito a para com uma exceçao
        this.time = 0;
        try {
            while (true) {
                updateGame();
            }
        }
        catch(ScreenClose e)
        {
            System.out.println("Player pressed Z, back to Main Menu....");
        } catch (InterruptedException | RightScreen | LeftScreen | UpScreen | DownScreen statusOverflow) {
            statusOverflow.printStackTrace();
        }
        catch (HealthOVF healthOVF){
            System.out.println("You lose! Back to Main Menu....");
        }
    }


    private void populateGame(int width, int height) throws OutOfBoundaries {
        Random random = new Random();
        TerminalElementFactory factory = new TerminalElementFactory();
        ElementType[] types = ElementType.values();
        for(int i = 0; i < 50; i++){
            int x = random.nextInt(width * 3);
            int y = random.nextInt(height * 3);
            int index = (x/width) + (y/height) * 3;
            Position pos = new Position(x, y, width, height, index);
            InteractableElementView element = factory.getElement(types[random.nextInt(types.length - 1)], pos);
            this.elements.addElement(element);
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

    void setTime(int time){
        this.time = time;
    }
}
