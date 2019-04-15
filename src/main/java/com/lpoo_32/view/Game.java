package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;
import com.lpoo_32.model.Status;

import java.io.IOException;


public class Game extends Display{

    TextGraphics graphics;

    public Game() throws IOException {
        super();
        graphics =  this.screen.newTextGraphics();
        this.setInitialProps();
    }

    public void run() throws IOException {
        draw();

        do{
            //screen clear estava a jabardar
            draw();
            this.screen.refresh();
        }while(true);
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

        for(ElementView drawable: this.props)
            drawable.draw(graphics);



    }

    private void setInitialProps(){
        //TODO Add Actual Player model values to the Bars
        this.props.add(new StatusBar(new Status(35), "#990000"));
        this.props.add(new PlayerView(new PlayerModel(new Position(2,2))));
    }
}
