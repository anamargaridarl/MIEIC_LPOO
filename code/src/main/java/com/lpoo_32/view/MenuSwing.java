package com.lpoo_32.view;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;

import java.awt.event.KeyListener;
import java.io.IOException;

public class MenuSwing implements Runnable{

    MenuSwingGUI gui;
    boolean gameRunning;

    public MenuSwing() {
        gameRunning = false;
        gui = new MenuSwingGUI(new Selector("monster.png", this));
    }

    public void run() {
        gui.setVisible(true);
        while (!gameRunning){
            gui.draw();
            try {
                Thread.sleep(1000/30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void initiateGame() throws OutOfBoundaries, IOException {
        Elements elements = new Elements();
        PlayerModel model = new PlayerModel(new Position(2,2, Game.width/4, Game.height/4, 0));
        GameController game = new GameController(
                elements,
                model,
                new GameSwing(gui, model, elements)
        );
//        KeyListener[] keys = gui.getKeyListeners();
//        for (KeyListener key : keys) {
//            gui.removeKeyListener(key);
//        }
        gui.addKeyListener(new SwingKeyboard(game));
        gameRunning = true;
        game.run();
        gameRunning = false;
    }

}
