package com.lpoo_32.view;

import com.lpoo_32.exceptions.OutOfBoundaries;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MenuSwingKeyboard implements KeyListener {
    private final Selector selector;

    MenuSwingKeyboard(Selector selector){
        this.selector = selector;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
                selector.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                selector.moveDown();
            case KeyEvent.VK_ENTER:
                try {
                    selector.enter();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (OutOfBoundaries outOfBoundaries) {
                    outOfBoundaries.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
