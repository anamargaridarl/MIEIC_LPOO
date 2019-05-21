package com.lpoo_32.controller;

import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.*;
import com.lpoo_32.view.*;

import java.io.IOException;
import java.util.Random;

public class GameController
{
    private final Game game;
    private PlayerModel player;
    private Elements elements;
    private TerminalKeyboard keyboardProcessor;
    private NourishState hunger;
    private NourishState thirst;
    private NourishState sleep;
    private static final int frameRate = 60;
    private int time;
    private ElementFactory factory;


    public GameController(DisplayProps props, Elements elements, PlayerModel player) throws OutOfBoundaries {
        this.player = player;
        this.elements = elements;
        this.keyboardProcessor = new TerminalKeyboard(props.getScreen());
        this.hunger = new SatedState(player);
        this.thirst = new QuenchedState(player);
        this.sleep = new DayState(player);
        this.factory = new TerminalElementFactory();
        this.populateGame(Game.width/4, Game.height/4);
        this.buildHouse(Game.width/4, Game.height/4);
        this.game = new Game(props, this.player, elements);
    }

    void processKey(EventType event) throws ScreenClose, HealthOVF, HungerRestored, HungerOVF, ThirstRestored, ThirstOVF, UpScreen, LeftScreen, RightScreen, DownScreen, Bedtime {

        SpikesModel spikes = new SpikesModel(10, null);
        if(event != EventType.NULL && event != null){
            switch (event) {
                case MOVEUP:
                    if(this.collisions(player.getPosition().checkMovementUp()))
                        player.moveUp();
                    break;
                case MOVEDOWN:
                    if(this.collisions(player.getPosition().checkMovementDown()))
                        player.moveDown();
                    break;
                case MOVELEFT:
                    if(this.collisions(player.getPosition().checkMovementLeft()))
                        player.moveLeft();
                    break;
                case MOVERIGHT:
                    if(this.collisions(player.getPosition().checkMovementRight()))
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

        }
    }

    void updateGame() throws IOException, ScreenClose, HealthOVF, InterruptedException, RightScreen, LeftScreen, UpScreen, DownScreen {
        try {
            this.processKey(this.keyboardProcessor.processKey());
            this.game.draw();
            Thread.sleep(1000/ frameRate);
            updateNourishment();
        } catch (HungerRestored hungerRestored) {
            this.hunger = new SatedState(player);
        } catch (HungerOVF nourishOVF) {
            this.hunger = new FamishState(player);
        } catch (ThirstRestored thirstRestored) {
            this.thirst = new QuenchedState(player);
        } catch (ThirstOVF thirstOVF) {
            this.thirst = new FamishState(player);
        } catch (RightScreen rightScreen) {
            if(this.game.getIndex()%3  != 2){
                this.game.setIndex(this.game.getIndex() + 1);
                this.player.getPosition().setIndex(this.game.getIndex());
            }
        } catch (LeftScreen leftScreen) {
            if(this.game.getIndex()%3  != 0){
                this.game.setIndex(this.game.getIndex() - 1);
                this.player.getPosition().setIndex(this.game.getIndex());
            }
        } catch (UpScreen upScreen) {
            if(this.game.getIndex() - 3  >= 0){
                this.game.setIndex(this.game.getIndex() - 3);
                this.player.getPosition().setIndex(this.game.getIndex());
            }
        } catch (DownScreen downScreen) {
            if(this.game.getIndex() + 3  < 9){
                this.game.setIndex(this.game.getIndex() + 3);
                this.player.getPosition().setIndex(this.game.getIndex());
            }
        } catch (Bedtime bedtime) {
            this.sleep = new SleepState(player);
        } catch (Sleeptime sleeptime) {
            this.sleep = new DayState(player);
        }
    }

    private void updateNourishment() throws HungerOVF, ThirstOVF, HealthOVF, HungerRestored, ThirstRestored, Sleeptime {
        time++;
        this.thirst.update(time);
        this.hunger.update(time);
        this.sleep.update(time);
    }

    public void run() throws IOException {
        this.game.draw();

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
        ElementType[] types = ElementType.values();
        for(int i = 0; i < 50; i++){
            int x = random.nextInt(width * 3);
            int y = random.nextInt(height * 3);
            int index = (x/width) + (y/height) * 3;
            Position pos = new Position(x, y, width, height, index);
            InteractableElementView element = factory.getElement(types[random.nextInt(types.length - 4)], pos);
            this.elements.addElement(element);
        }
    }

    private void buildHouse(int width, int height) throws OutOfBoundaries {
        Random random = new Random();
        int initialX = random.nextInt(width * 3 - 5);
        int initialY = random.nextInt(height * 3 - 6) + 1;
        System.out.println("X: " + initialX + " Y: " + initialY);
        for(int i = 0; i < 5; i++){
            if(i == 0 || i == 4){
                for(int j = 0; j < 5; j++){
                    addHousePart(width, height, initialX, initialY, i, j, ElementType.WALL);
                }
            }else{
                if(i == 2){
                    addHousePart(width, height, initialX, initialY, i, 0, ElementType.DOOR);
                    addHousePart(width, height, initialX, initialY, i, 4, ElementType.WALL);
                }else {
                    addHousePart(width, height, initialX, initialY, i, 0, ElementType.WALL);
                    addHousePart(width, height, initialX, initialY, i, 4, ElementType.WALL);
                }
            }
        }
        addHousePart(width, height, initialX, initialY, 1, 1, ElementType.BED);
        addHousePart(width, height, initialX, initialY, 1, 2, ElementType.BED);
    }

    private void addHousePart(int width, int height, int initialX, int initialY, int i, int j, ElementType type) throws OutOfBoundaries {
        int index = ((initialX + i)/width) + ((initialY + j)/height) * 3;
        this.elements.addElement(factory.getElement(type, new Position(i + initialX, j + initialY, width, height, index)));
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
    boolean collisions(Position position) throws HungerRestored, HungerOVF, ThirstRestored, ThirstOVF, HealthOVF, Bedtime {

        if (elements.getView(position) != null && !(isCatchable(position))) {
            return elements.getModel(position).interact(player);
        }
        return true;
    }

    void setTime(int time){
        this.time = time;
    }
}
