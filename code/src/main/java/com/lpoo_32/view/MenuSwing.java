package com.lpoo_32.view;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MenuSwing implements Runnable{

    MenuSwingGUI gui;
    boolean gameRunning;
    GameController game;

    public MenuSwing() {
        gameRunning = false;
        gui = new MenuSwingGUI(new Selector(this));
    }

    public void run() {
        gui.setVisible(true);
        while (true){
            while (!gameRunning){
                gui.draw();
                try {
                    Thread.sleep(1000/30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                game.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameRunning = false;
        }
    }
    public void initiateGame() {
        newGame();
        gameRunning = true;

    }

    private void newGame() {
        Elements elements = new Elements();
        try {
            PlayerModel model = new PlayerModel(new Position(2, 2, Game.width / 4, Game.height / 4, 0));
            game = new GameController(
                    elements,
                    model,
                    new GameSwing(gui, model, elements)
            );
        } catch (OutOfBoundaries outOfBoundaries) {
            outOfBoundaries.printStackTrace();
        }
        gui.addKeyListener(new SwingKeyboard(game));
    }
}
