package com.lpoo_32.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HelpMenuSwing implements KeyListener {

    private final HelpMenuGUI help;

    HelpMenuSwing(HelpMenuGUI help){
        this.help = help;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_Q:
                help.stopDrawing();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
