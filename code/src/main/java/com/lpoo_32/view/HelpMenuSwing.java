package com.lpoo_32.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class HelpMenuSwing implements KeyListener {

    private final HelpMenuGUI help;

    HelpMenuSwing(HelpMenuGUI help){
        this.help = help;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_Q) {
            help.stopDrawing();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
