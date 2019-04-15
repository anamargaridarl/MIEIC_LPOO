package com.lpoo_32.Controller;

import java.io.IOException;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.model.PlayerModel;

public class Keyboard
{
    private PlayerModel player;

    public Keyboard(PlayerModel player)
    {
        this.player = player;
    }

    public void processKey(Screen screen) throws IOException {

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
                }
        }
    }
}
