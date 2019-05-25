package com.lpoo_32.view;

import com.lpoo_32.controller.GameController;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class SwingKeyboard implements KeyListener {
    private final GameController controller;

    public SwingKeyboard(GameController controller){
        this.controller = controller;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        EventType event = null;
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
                event = EventType.MOVEUP;
                break;
            case KeyEvent.VK_DOWN:
                event = EventType.MOVEDOWN;
                break;
            case KeyEvent.VK_LEFT:
                event = EventType.MOVELEFT;
                break;
            case KeyEvent.VK_RIGHT:
                event = EventType.MOVERIGHT;
                break;
            case KeyEvent.VK_Z:
                event = EventType.EXIT;
                break;
            case KeyEvent.VK_P:
                event = EventType.NULL;
                break;
            case KeyEvent.VK_F:
                event = EventType.STORE;
                break;
            case KeyEvent.VK_T:
                event = EventType.USE;
                break;
            case KeyEvent.VK_Q:
                event = EventType.LEFTINVENTORY;
                break;
            case KeyEvent.VK_W:
                event = EventType.LEFTINVENTORY;
                break;
            case KeyEvent.VK_E:
                event = EventType.LEFTINVENTORY;
                break;
            default:
                System.out.println(keyEvent.getKeyChar());
        }
        this.controller.processKey(event);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}
