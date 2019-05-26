package com.lpoo_32.controller;

import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.*;
import com.lpoo_32.view.*;

import java.io.IOException;
import java.util.Random;

import static com.lpoo_32.model.Attacks.*;

public class GameController
{
    private final Game game;
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
        this.populateGame(Game.width/4, Game.height/4,getRandomIndex());
        this.game = new Game(props, this.player, elements);
    }

    int getRandomIndex()
    {
        Random random = new Random();
        return random.nextInt(9);
    }

    void processKey(EventType event) throws ScreenClose, HealthOVF, HungerRestored, HungerOVF, ThirstRestored, ThirstOVF, UpScreen, LeftScreen, RightScreen, DownScreen {

        InteractableElementView i;
        SpikesModel spikes = new SpikesModel(10, null);
        if(event != EventType.NULL && event != null){
            switch (event) {
                case MOVEUP:
                    i = elements.getView(player.getPosition().checkMovementUp());
                    if(i !=  null) {
                        if (i instanceof MonsterView)
                            break;
                    }
                    player.moveUp();
                    break;
                case MOVEDOWN:
                    i = elements.getView(player.getPosition().checkMovementDown());
                    if(i !=  null)
                    {
                        if(i instanceof  MonsterView)
                            break;
                    }
                    player.moveDown();
                    break;
                case MOVELEFT:
                    i = elements.getView(player.getPosition().checkMovementLeft());
                    if(i !=  null) {
                        if (i instanceof MonsterView)
                            break;
                    }
                            player.moveLeft();

                    break;
                case MOVERIGHT:
                    i = elements.getView(player.getPosition().checkMovementRight());
                    if(i !=  null) {
                        if (i instanceof MonsterView)
                            break;
                    }
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
                        if (isCatchable(player.getPosition())) {
                            player.addElementInventory((CatchableView) elements.getView(player.getPosition()));
                            removeElementProps(elements.getModel(player.getPosition()));
                        }
                    break;
                case USE:
                    if (isCatchable(player.getPosition())) {
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
                    if (player.getInventory().getElement() != null) {
                        if (!changeWeaponInventory()) {
                            player.getInventory().getElement().interact(player);
                            player.getInventory().removeElement();
                        }

                    }
                    break;
                case ATTACKUP:
                    checkForMonsterAndAttack(this.player.getPosition(),AUP,this.player.getWeapon());
                    break;
                case ATTACKDOWN:
                    checkForMonsterAndAttack(this.player.getPosition(),ADOWN,this.player.getWeapon());
                    break;
                case ATTACKLEFT:
                    checkForMonsterAndAttack(this.player.getPosition(),ALEFT,this.player.getWeapon());
                    break;
                case ATTACKRIGHT:
                    checkForMonsterAndAttack(this.player.getPosition(),ARIGHT,this.player.getWeapon());
                    break;
            }

            this.collisions(player.getPosition());
        }
    }

    void updateGame() throws IOException, ScreenClose, HealthOVF, InterruptedException, RightScreen, LeftScreen, UpScreen, DownScreen {
        try {
            this.processKey(this.keyboardProcessor.processKey());
            this.game.draw();
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

    private Position randomPosition(int width, int height, int indexGame) throws OutOfBoundaries {
        Random random = new Random();
        int x = random.nextInt(width * 3);
        int y = random.nextInt(height * 3);
        int index = (x/width) + (y/height) * 3;
        Position pos = new InteractablePosition(x, y, width, height, index,indexGame);
        return pos;
    }

    private void populateGame(int width, int height, int indexGame) throws OutOfBoundaries {
        Random random = new Random();
        TerminalElementFactory factory = new TerminalElementFactory();
        ElementType[] types = ElementType.values();
        InteractableElementView element;
        for(int i = 0; i < 60; i++){
            Position pos = randomPosition(width,height,indexGame);
            element = factory.getElement(types[random.nextInt(types.length - 1)], pos);
            this.elements.addElement(element);
        }

        for(int i = 0; i < 30; i++){
            Position pos = randomPosition(width,height,indexGame);
            element = new MonsterView(new MonsterModel(pos, 15, new MovableElement(pos),this,player.getPosition()));
            this.elements.addElement(element);
        }

        for(int i = 0; i <3; i++)
        {
            Position pos = randomPosition(width,height,indexGame);
            element = new WeaponView(new WeaponModel(pos, (random.nextInt(15-5)+5)));
            this.elements.addElement(element);
        }

    }


    //---------------------------------------------------------------
    //remove element from view array
    public void removeElementProps(InteractableElement element) {
        this.elements.removeElement(element);
    }

    public boolean addElementProps(InteractableElementView element)  {

            return this.elements.addElement(element);
    }

    public boolean checkForElement(Position position)
    {
        if(elements.getView(position) == null)
            return false;
        else
            return true;
    }


    public boolean monsterEqualsPlayer(Position player, Position position) {

        if (Math.abs(position.getX() - player.getX()) == 0 && Math.abs(position.getY() - player.getY()) == 1
                || Math.abs(position.getX() - player.getX()) == 1 && Math.abs(position.getY() - player.getY()) == 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean changeWeaponInventory() {
        if (player.getInventory().getElement() instanceof WeaponModel) {

            if (player.getWeapon() != null) {
                WeaponModel old = player.getWeapon();
                player.setWeapon((WeaponModel) player.getInventory().getElement());
                player.getInventory().removeElement();
                player.getInventory().addElement(new WeaponView(old));
            }
            else {
                player.setWeapon((WeaponModel) player.getInventory().getElement());
                player.getInventory().removeElement();
            }
            return true;
        }

        return false;

    }

    private boolean isCatchable(Position position) {

        if (elements.getView(position) != null) {
            return elements.getModel(position) instanceof CatchableElement;
        }
        else
            return false;

    }

    //handles colisions for non catchable elements
    public void collisions(Position position) throws HungerRestored, ThirstOVF, HealthOVF, HungerOVF, ThirstRestored { //TODO: Mo {

        if (elements.getView(position) != null && !(isCatchable(position))) {
            elements.getModel(position).interact(player);
        }

    }

    public void checkCollisionMonster(Position position) throws HungerRestored, ThirstOVF, HealthOVF, HungerOVF, ThirstRestored {

        if(monsterEqualsPlayer(player.getPosition(), position)){
            elements.getModel(position).interact(player);
        }

    }


    public void checkForMonsterAndAttack(Position position, Attacks orientation, WeaponModel weapon) throws HungerRestored, ThirstOVF, HealthOVF, HungerOVF, ThirstRestored, LeftScreen, RightScreen, DownScreen, UpScreen {

        if(weapon == null)
            return;
        InteractableElementView element;

        switch (orientation) {
            case AUP:
                element = elements.getView(position.checkMovementUp());
                if(element != null) {
                    if (element instanceof MonsterView)
                        weapon.interactMonster(((MonsterView) element).getMonster());
                }
                break;
            case ADOWN:
                element = elements.getView(position.checkMovementDown());
                if(element != null) {
                    if (element instanceof MonsterView)
                        weapon.interactMonster(((MonsterView) element).getMonster());
                }
                break;
            case ALEFT:
                element = elements.getView(position.checkMovementLeft());
                if(element != null) {
                    if (element instanceof MonsterView)
                        weapon.interactMonster(((MonsterView) element).getMonster());
                }
                break;
            case ARIGHT:
                element = elements.getView(position.checkMovementRight());
                if(element != null ) {
                    if (element instanceof MonsterView)
                        weapon.interactMonster(((MonsterView) element).getMonster());
                }
                break;
            default:
                break;


        }
    }



    void setTime(int time){
        this.time = time;
    }
}
