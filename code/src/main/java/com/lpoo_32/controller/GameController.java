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
    private NourishState hunger;
    private NourishState thirst;
    private NourishState sleep;
    private static final int frameRate = 60;
    private int time;
    private ElementFactory factory;
    private boolean screenclose;


    public GameController(Elements elements, PlayerModel player, Game game) throws OutOfBoundaries {
        this.player = player;
        this.elements = elements;
        this.screenclose = false;
        restoreHunger(player);
        restoreThirst(player);
        this.sleep = new DayState(player);
        this.factory = new TerminalElementFactory();
        this.populateGame(Game.width/4, Game.height/4,getRandomIndex());
        this.buildHouse(Game.width/4, Game.height/4);
        this.game = game;
    }

    private int getRandomIndex()
    {
        Random random = new Random();
        return random.nextInt(9);
    }

    public void processKey(EventType event) {

        InteractableElementView i;
        try{
            if(event != EventType.NULL && event != null){
                switch (event) {
                    case MOVEUP:
                        i = elements.getView(player.getPosition().checkMovementUp());
                        if(i !=  null) {
                            if (i instanceof MonsterView)
                                break;
                        }
                        if(this.collisions(player.getPosition().checkMovementUp()))
                            player.moveUp();
                        break;
                    case MOVEDOWN:
                        i = elements.getView(player.getPosition().checkMovementDown());
                        if(i !=  null) {
                            if(i instanceof  MonsterView)
                                break;
                        }
                        if(this.collisions(player.getPosition().checkMovementDown()))
                            player.moveDown();
                        break;
                    case MOVELEFT:
                        i = elements.getView(player.getPosition().checkMovementLeft());
                        if(i !=  null) {
                            if (i instanceof MonsterView)
                                break;
                        }
                        if(this.collisions(player.getPosition().checkMovementLeft()))
                            player.moveLeft();
                        break;
                    case MOVERIGHT:
                        i = elements.getView(player.getPosition().checkMovementRight());
                        if(i !=  null) {
                            if (i instanceof MonsterView)
                                break;
                        }
                        if(this.collisions(player.getPosition().checkMovementRight()))
                            player.moveRight();
                        break;
                    case EXIT:
                        throw new ScreenClose();
                    case NULL:
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
                    case USE: //use water/food in moment
                        if(!isWeaponUseDirect(player.getPosition())) {
                            System.out.println("bananas");
                            if (isCatchable(player.getPosition())) {
                                elements.getModel(player.getPosition()).interact(player);
                                removeElementProps(elements.getModel(player.getPosition()));
                            }
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

            }
        }catch (HungerRestored hungerRestored) {
            restoreHunger(player);
        } catch (HungerOVF nourishOVF) {
            hungerOVF();
        } catch (ThirstRestored thirstRestored) {
            restoreThirst(player);
        } catch (ThirstOVF thirstOVF) {
            thirstOVF();
        } catch (RightScreen rightScreen) {
            horizontalScreen(2, this.game.getIndex() + 1);
        } catch (LeftScreen leftScreen) {
            horizontalScreen(0, this.game.getIndex() - 1);
        } catch (UpScreen upScreen) {
            upScreen();
        } catch (DownScreen downScreen) {
            downScreen();
        } catch (Bedtime bedtime) {
            this.sleep = new SleepState(player);
        } catch (ScreenClose | HealthOVF screenClose) {
            this.screenclose = true;
        }
    }

    void updateGame() throws IOException, HealthOVF, InterruptedException, ScreenClose {
        try {
            this.game.draw();
            Thread.sleep(1000/ frameRate);
            updateNourishment();
            if(screenclose)
                throw new ScreenClose();
        } catch (HungerRestored hungerRestored) {
            restoreHunger(player);
        } catch (HungerOVF nourishOVF) {
            hungerOVF();
        } catch (ThirstRestored thirstRestored) {
            restoreThirst(player);
        } catch (ThirstOVF thirstOVF) {
            thirstOVF();
        } catch (Sleeptime sleeptime) {
            this.sleep = new DayState(player);
        }  catch (RightScreen rightScreen) {
            horizontalScreen(2, this.game.getIndex() + 1);
        } catch (LeftScreen leftScreen) {
            horizontalScreen(0, this.game.getIndex() - 1);
        } catch (UpScreen upScreen) {
            upScreen();
        } catch (DownScreen downScreen) {
            downScreen();
        } catch (Bedtime bedtime) {
            this.sleep = new SleepState(player);
        }
    }

    private void downScreen() {
        if (this.game.getIndex() + 3 < 9) {
            this.game.setIndex(this.game.getIndex() + 3);
            this.player.getPosition().setIndex(this.game.getIndex());
        }
    }

    private void thirstOVF() {
        this.thirst = new FamishState(player);
    }

    private void hungerOVF() {
        this.hunger = new FamishState(player);
    }

    private void updateNourishment() throws HungerOVF, ThirstOVF, HealthOVF, HungerRestored, ThirstRestored, Sleeptime {
        time++;
        this.thirst.update(time);
        this.hunger.update(time);
        this.sleep.update(time);
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
        } catch (InterruptedException statusOverflow) {
            statusOverflow.printStackTrace();
        }
        catch (HealthOVF healthOVF){
            System.out.println("You lose! Back to Main Menu....");
        }
    }

    private void upScreen() {
        if (this.game.getIndex() - 3 >= 0) {
            this.game.setIndex(this.game.getIndex() - 3);
            this.player.getPosition().setIndex(this.game.getIndex());
        }
    }

    private void horizontalScreen(int i, int i2) {
        if (this.game.getIndex() % 3 != i) {
            this.game.setIndex(i2);
            this.player.getPosition().setIndex(this.game.getIndex());
        }
    }

    private void restoreThirst(PlayerModel player) {
        this.thirst = new QuenchedState(player);
    }

    private void restoreHunger(PlayerModel player) {
        this.hunger = new SatedState(player);
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
            element = factory.getElement(types[random.nextInt(types.length - 4)], pos);
            this.elements.addElement(element);
        }

        for(int i = 0; i < 30; i++){
            Position pos = randomPosition(width,height,indexGame);
            element = new MonsterView(new MonsterModel(pos, 15, new MovableElement(pos, "monster.png"),this,player.getPosition()));
            this.elements.addElement(element);
        }

        for(int i = 0; i <3; i++)
        {
            Position pos = randomPosition(width,height,indexGame);
            element = new WeaponView(new WeaponModel(pos, (random.nextInt(15-5)+5)));
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
    }

    private void addHousePart(int width, int height, int initialX, int initialY, int i, int j, ElementType type) throws OutOfBoundaries {
        int index = ((initialX + i)/width) + ((initialY + j)/height) * 3;
        this.elements.forceAddElement(factory.getElement(type, new Position(i + initialX, j + initialY, width, height, index)));
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


    public void changeActualWeapon(Position position)
    {
        if (player.getWeapon() != null) {
            WeaponModel old = player.getWeapon();
            player.setWeapon((WeaponModel) elements.getView(position).getElement());
            player.getInventory().addElement(new WeaponView(old));
        }
        else {
            player.setWeapon((WeaponModel) elements.getView(position).getElement());
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

    public boolean isWeaponUseDirect(Position position)
    {
        if (elements.getView(position) != null) {
            if(elements.getView(position) instanceof WeaponView) {
                changeActualWeapon(position);
                removeElementProps(elements.getModel(player.getPosition()));
                return true;
            }
            return false;
        }
        return false;
    }
    //handles colisions for non catchable elements
    boolean collisions(Position position) throws HungerRestored, HungerOVF, ThirstRestored, ThirstOVF, HealthOVF, Bedtime {

        if (elements.getView(position) != null && !(isCatchable(position))) {
            return elements.getModel(position).interact(player);
        }
        return true;
    }

    public void checkCollisionMonster(Position position) throws HungerRestored, ThirstOVF, HealthOVF, HungerOVF, ThirstRestored, Bedtime {

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
