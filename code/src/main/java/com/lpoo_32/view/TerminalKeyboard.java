package com.lpoo_32.view;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.controller.GameController;
import com.lpoo_32.controller.action.*;

import java.io.IOException;


public class TerminalKeyboard implements KeyboardAnalyzer {


    private Screen screen;
    private GameController controller;
    private boolean flag;

    public TerminalKeyboard(Screen screen, GameController controller){
        this.screen = screen;
        this.controller = controller;
        this.flag = true;
        new Thread(() -> {
            try {
                this.processKey();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void processKey() throws IOException {
        while (flag){
            ActionEvent event = null;
            KeyStroke key = screen.readInput();
            if (key != null) {
                switch (key.getKeyType()) {
                    case ArrowUp:
                        event = new MoveUp(controller);
                        break;
                    case ArrowDown:
                        event = new MoveDown(controller);
                        break;
                    case ArrowLeft:
                        event = new MoveLeft(controller);
                        break;
                    case ArrowRight:
                        event = new MoveRight(controller);
                        break;
                    case Character:
                        switch (key.getCharacter()) {
                            case 'z':
                                event = new Exit();
                                break;
                            case 'h':
                                event = new Null();
                                //TODO: How to proceed with this?
                                System.out.println("Open help Menu");
                                break;
                            case 'f': //add element to inventory
                                event = new Store(controller);
                                break;
                            case 't': //use water/food in moment
                                event = new Use(controller);
                                break;
                            case '1': //move left in inventory
                                event = new LeftInventory(controller.getPlayer());
                                break;
                            case '2': //move right in inventory
                                event = new RightInventory(controller.getPlayer());
                                break;
                            case 'e':
                                event = new InventoryUse(controller);
                                break;
                            case 'w':
                                event = new AttackUp(controller);
                                break;
                            case 'd':
                                event = new AttackRight(controller);
                                break;
                            case 'a':
                                event = new AttackLeft(controller);
                                break;
                            case 's':
                                event = new AttackDown(controller);
                                break;
                            default:
                                event = new Null();
                                break;
                        }
                        break;
                    default:
                        event = new Null();
                        break;
                }
            }
            this.controller.processKey(event);
        }
    }

    void stopKeyboard(){
        this.flag = false;
    }

}
