package com.lpoo_32.view;

import com.lpoo_32.controller.ExceptionableRunnable;
import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

class MenuSwing implements Runnable{

    private final MenuSwingGUI gui;
    private boolean gameRunning;
    private ExceptionableRunnable runnable;
    private boolean running = true;

    public MenuSwing() {
        gameRunning = false;
        gui = new MenuSwingGUI(new Selector(this));
    }

    public void run() {
        gui.setVisible(true);
        while (running){
            while (!gameRunning){
                gui.draw();
                try {
                    Thread.sleep(1000/30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                runnable.run();
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
            runnable = new GameController(
                    elements,
                    model,
                    new GameSwing(gui, model, elements)
            );
        } catch (OutOfBoundaries outOfBoundaries) {
            outOfBoundaries.printStackTrace();
        }
        gui.addKeyListener(new SwingKeyboard((GameController) runnable));
    }

    public void initiateHelp() {
        runnable = new HelpMenuGUI(gui);
        gameRunning = true;
        gui.addKeyListener(new HelpMenuSwing((HelpMenuGUI) runnable));
    }

    public void stopGame(){
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
        running = false;
    }
}
