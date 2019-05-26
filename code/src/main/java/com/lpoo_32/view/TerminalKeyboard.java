package com.lpoo_32.view;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;

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
            EventType event = null;
            KeyStroke key = screen.readInput();
            if (key != null) {
                switch (key.getKeyType()) {
                    case ArrowUp:
                        event = EventType.MOVEUP;
                        break;
                    case ArrowDown:
                        event = EventType.MOVEDOWN;
                        break;
                    case ArrowLeft:
                        event = EventType.MOVELEFT;
                        break;
                    case ArrowRight:
                        event = EventType.MOVERIGHT;
                        break;
                    case Character:
                        switch (key.getCharacter()) {
                            case 'z':
                                event = EventType.EXIT;
                                break;
                            case 'p':
                                event = EventType.NULL;
                                break;
                            case 'h':
                                event = EventType.HELP;
                                //TODO: How to proceed with this?
                                System.out.println("Open help Menu");
                                break;
                            case 'f': //add element to inventory
                                event = EventType.STORE;
                                break;
                            case 't': //use water/food in moment
                                event = EventType.USE;
                                break;
                            case '1': //move left in inventory
                                event = EventType.LEFTINVENTORY;
                                break;
                            case '2': //move right in inventory
                                event = EventType.RIGHTINVENTORY;
                                break;
                            case 'e':
                                event = EventType.INVETORYUSE;
                                break;
                            case 'w':
                                event = EventType.ATTACKUP;
                                break;
                            case 'd':
                                event = EventType.ATTACKRIGHT;
                                break;
                            case 'a':
                                event = EventType.ATTACKLEFT;
                                break;
                            case 's':
                                event = EventType.ATTACKDOWN;
                                break;
                            default:
                                event = EventType.NULL;
                                break;
                        }
                        break;
                    default:
                        event = EventType.NULL;
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
