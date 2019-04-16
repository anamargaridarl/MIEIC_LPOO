package com.lpoo_32.Controller;

import java.io.IOException;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.ScreenClose;
import com.lpoo_32.exceptions.StatusOverflow;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;

public class Keyboard
{
    private PlayerModel player;
    private Elements elements;

    public Keyboard(PlayerModel player, Elements elements)
    {
        this.player = player;
        this.elements = elements;
    }

    public void processKey(Screen screen) throws IOException, ScreenClose{

        KeyStroke key;
        key = screen.readInput();

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
                if (key.getCharacter() == 'q') {
                screen.close();
                throw new ScreenClose();
                }
        }

        this.colisions(player.getPosition());
    }


    public void colisions(Position position)
    {
        try {
            if(elements.getValue(position) != null)
                elements.getValue(position).interact(player);
        }
        catch(StatusOverflow e)
        {
            System.out.printf("Status Overflow");
            System.exit(0);
        }

    }

}
