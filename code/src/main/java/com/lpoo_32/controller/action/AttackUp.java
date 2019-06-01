package com.lpoo_32.controller.action;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.view.InteractableElementView;
import com.lpoo_32.view.MonsterView;

public class AttackUp implements ActionEvent {
    private final GameController controller;

    public AttackUp(GameController controller){
        this.controller = controller;
    }
    @Override
    public void execute() throws UpScreen {
        if(this.controller.getPlayer().getWeapon() == null)
            return;
        InteractableElementView element = controller.getElements().getView(controller.getPlayer().getPosition().checkMovementUp());
        if(element != null) {
            if (element instanceof MonsterView)
                this.controller.getPlayer().getWeapon().interactMonster(((MonsterView) element).getMonster());
        }
    }
}
