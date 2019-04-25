package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.controller.Keyboard;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Game extends Display{

    //    protected WindowBasedTextGUI gui;
    private List<List<ElementView>> props;
    private List<ElementView> generalView;
    private int index;
    private TextGraphics graphics;
    private Keyboard keyboard;
    private PlayerView player;
    private static final int frameRate = 60;
    private int time;
    private boolean hunger;
    private boolean thirst;
    private static final int width = 60;
    private static final int height = 50;

    Game(Screen screen) {
        super(screen);
        this.props = new ArrayList<>();
        this.generalView = new LinkedList<>();
        this.index = 0;
        this.initializePropsArray(9);
        this.setInitialProps();
        this.graphics =  this.screen.newTextGraphics();

        //probably needs to clean up
        this.keyboard = new Keyboard(this.player.getPlayer(),this.elements);
        this.hunger = false;
        this.thirst = false;
    }

    public void run() throws IOException {

        this.screen.clear();
        draw();
        this.screen.refresh();

        //isto parece shady; ter um ciclo infinito a para com uma exceçao
        this.time = 0;
        try {
            while (true) {
                updateGame();
            }
        }
        catch(ScreenClose e)
        {
            System.out.println("Player pressed Q, back to Main Menu....");
        } catch (InterruptedException | RightScreen | LeftScreen | UpScreen | DownScreen statusOverflow) {
            statusOverflow.printStackTrace();
        }
        catch (HealthOVF healthOVF){
            System.out.println("You lose! Back to Main Menu....");
        }
    }

    void updateGame() throws IOException, ScreenClose, HealthOVF, InterruptedException, RightScreen, LeftScreen, UpScreen, DownScreen {
        this.screen.clear();
        try {
            keyboard.processKey(screen);
            draw();
            this.screen.refresh();
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
            if(index%3  != 2){
                this.player.getPlayer().getPosition().setIndex(++index);
                this.player.getPlayer().moveRight();
            }
        } catch (LeftScreen leftScreen) {
            if(index%3  != 0){
                this.player.getPlayer().getPosition().setIndex(--index);
                this.player.getPlayer().moveLeft();
            }
        } catch (UpScreen upScreen) {
            if(index - 3  >= 0){
                this.index -= 3;
                this.player.getPlayer().getPosition().setIndex(index);
                this.player.getPlayer().moveUp();
            }
        } catch (DownScreen downScreen) {
            if(index + 3  <= 9){
                this.index += 3;
                this.player.getPlayer().getPosition().setIndex(index);
                this.player.getPlayer().moveDown();
            }
        }
    }

    private void updateNourishment() throws HealthOVF, HungerOVF, ThirstOVF {
        time++;
        if(time % (3600) == 0) {
            this.player.getPlayer().getWater().decreaseValue(5);
        }

        if(time % (5400.0) == 0) {
            this.player.getPlayer().getFood().decreaseValue(5);
        }
        if((this.hunger || this.thirst) && ((time % 120) == 0))
            this.player.getPlayer().getHealth().decreaseValue(5);
    }

    @Override
    public void draw(){


        graphics.setBackgroundColor(TextColor.Factory.fromString("#48D1CC"));
        graphics.fillRectangle(new TerminalPosition(0, 0),
                                new TerminalSize(ScreenSize.instance().getColumn(60),
                                ScreenSize.instance().getRows(50)), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        /*graphics.putString(new TerminalPosition(ScreenSize.instance().getColumn(20),
                            ScreenSize.instance().getRows(20)), "@");*/

        for(ElementView drawable: this.props.get(index)){
            drawable.draw(graphics);
        }

        for(ElementView drawable: this.generalView)
            drawable.draw(graphics);
    }

    private void initializePropsArray(int numScreens){
        for(int i = 0; i < numScreens; i++){
            props.add(new LinkedList<>());
        }
    }

    private void setInitialProps(){
        int width = Game.width/4;
        int height = Game.height/4;

        InteractableElement food = new FoodModel(10,new Position(2,3, width, height, 0));
        InteractableElement spike = new SpikesModel(30,new Position(4,4, width, height, 0));
        InteractableElement spike2 = new SpikesModel(10,new Position(6,4, width, height, 0));
        InteractableElement spike3 = new SpikesModel(30,new Position(18,5, width, height, 1));
        InteractableElement spike4 = new SpikesModel(10,new Position(29,3, width, height, 1));

        this.props.get(0).add(new FoodView((FoodModel) food));
        this.props.get(0).add(new SpikesView((SpikesModel) spike));
        this.props.get(0).add(new SpikesView((SpikesModel) spike2));

        this.props.get(1).add(new SpikesView((SpikesModel) spike3));
        this.props.get(1).add(new SpikesView((SpikesModel) spike4));

        this.elements.addElement(food);
        this.elements.addElement(spike);
        this.elements.addElement(spike2);
        this.elements.addElement(spike3);
        this.elements.addElement(spike4);
        this.player = new PlayerView(new PlayerModel(new Position(2,2, width, height, 0)));
        this.generalView.add(new StatusBar(player.getPlayer().getHealth(), "#990000", 10));
        this.generalView.add(new StatusBar(player.getPlayer().getFood(), "#3CB371", 14));
        this.generalView.add(new StatusBar(player.getPlayer().getWater(), "#87CEFA", 18));
        this.generalView.add(this.player);

    }
}
