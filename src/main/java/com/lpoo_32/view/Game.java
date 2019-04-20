package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.Controller.Keyboard;
import com.lpoo_32.exceptions.ScreenClose;
import com.lpoo_32.exceptions.StatusOverflow;
import com.lpoo_32.model.*;

import java.io.IOException;


public class Game extends Display{

    public static final int width = 47;
    public static final int height = 58;

    TextGraphics graphics;
    Keyboard keyboard;

    public Game() throws IOException {
        super();
        this.setInitialProps();
        this.graphics =  this.screen.newTextGraphics();

        //probably needs to clean up
        PlayerView a = (PlayerView) props.get(1);
        this.keyboard = new Keyboard(a.getPlayer(),elements);


    }

    public void run() throws IOException {

        this.screen.clear();
        draw();
        this.screen.refresh();

        //isto parece shady; ter um ciclo infinito a para com uma exceçao
        try {
            while (true) {
                this.screen.clear();
                keyboard.processKey(screen);
                draw();
                this.screen.refresh();
            }
        }
        catch(ScreenClose e)
        {
            System.exit(0);
        }
    }

    @Override
    public void draw() throws IOException {

        System.out.println(this.screen.getTerminalSize().getColumns() + " - " + this.screen.getTerminalSize().getRows());
        System.out.println(ScreenSize.instance().getColumn(60) + " - " + ScreenSize.instance().getRows(50));

        graphics.setBackgroundColor(TextColor.Factory.fromString("#48D1CC"));
        graphics.fillRectangle(new TerminalPosition(0, 0),
                                new TerminalSize(ScreenSize.instance().getColumn(60),
                                ScreenSize.instance().getRows(50)), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        /*graphics.putString(new TerminalPosition(ScreenSize.instance().getColumn(20),
                            ScreenSize.instance().getRows(20)), "@");*/

        for(ElementView drawable: this.props) {
            System.out.println("oi");
            drawable.draw(graphics);
        }




    }

    private void setInitialProps(){

        InteractableElement food = new FoodModel(10,new Position(2,3));
        InteractableElement spike = new SpikesModel(30,new Position(4,4));
        InteractableElement spike2 = new SpikesModel(10,new Position(6,4));

        //TODO Add Actual Player model values to the Bars
        this.props.add(new StatusBar(new Status(35), "#990000"));
        this.props.add(new PlayerView(new PlayerModel(new Position(2,2))));
        this.props.add(new FoodView((FoodModel) food));
        this.props.add(new SpikesView((SpikesModel) spike));
        this.props.add(new SpikesView((SpikesModel) spike2));

        this.elements.addElement(food);
        this.elements.addElement(spike);
        this.elements.addElement(spike2);
    }
}
