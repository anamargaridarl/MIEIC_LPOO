package com.lpoo_32.view;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.controller.action.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        ActionEvent event;
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
                event = new MoveUp(controller);
                break;
            case KeyEvent.VK_DOWN:
                event = new MoveDown(controller);
                break;
            case KeyEvent.VK_LEFT:
                event = new MoveLeft(controller);
                break;
            case KeyEvent.VK_RIGHT:
                event = new MoveRight(controller);
                break;
            case KeyEvent.VK_Z:
                event = new Exit();
                break;
            case KeyEvent.VK_P:
                event = new Null();
                break;
            case KeyEvent.VK_F:
                event = new Store(controller);
                break;
            case KeyEvent.VK_T:
                event = new Use(controller);
                break;
            case KeyEvent.VK_2:
                event = new RightInventory(controller.getPlayer());
                break;
            case KeyEvent.VK_1:
                event = new LeftInventory(controller.getPlayer());
                break;
            case KeyEvent.VK_E:
                event = new InventoryUse(controller);
                break;
            case KeyEvent.VK_W:
                event = new AttackUp(controller);
                break;
            case KeyEvent.VK_A:
                event = new AttackLeft(controller);
                break;
            case KeyEvent.VK_D:
                event = new AttackRight(controller);
                break;
            case KeyEvent.VK_S:
                event = new AttackDown(controller);
                break;
            default:
                event = new Null();
                System.out.println(keyEvent.getKeyChar());
        }
        this.controller.processKey(event);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}
