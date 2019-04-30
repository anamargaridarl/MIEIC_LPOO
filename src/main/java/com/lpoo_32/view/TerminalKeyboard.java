package com.lpoo_32.view;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ScreenClose;
import com.lpoo_32.exceptions.ThirstOVF;
import com.lpoo_32.model.SpikesModel;

import java.io.IOException;


public class TerminalKeyboard {
    public EventType processKey(Screen screen) throws IOException, ScreenClose, HealthOVF, HungerOVF, ThirstOVF {

        EventType event;
        KeyStroke key;
        key = screen.pollInput();
        SpikesModel spikes = new SpikesModel(10, null);
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
                        case 'q': //move left in inventory
                            event = EventType.LEFTINVENTORY;
                            break;
                        case 'w': //move right in inventory
                            event = EventType.RIGHTINVENTORY;
                            break;
                        case 'e':
                            event = EventType.INVETORYUSE;
                        default:
                            event = EventType.NULL;
                            break;
                    }
                default:
                    event = EventType.NULL;
            }
            return event;
        }
        return null;
    }
}
