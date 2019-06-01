package com.lpoo_32.controller.action;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.view.InteractableElementView;
import com.lpoo_32.view.MonsterView;

public class AttackLeft implements ActionEvent {    private final GameController controller;

    public AttackLeft(GameController controller){
        this.controller = controller;
    }
    @Override
    public void execute() throws LeftScreen {
        if(this.controller.getPlayer().getWeapon() == null)
            return;
        InteractableElementView element = controller.getElements().getView(controller.getPlayer().getPosition().checkMovementLeft());
        if(element != null) {
            if (element instanceof MonsterView)
                this.controller.getPlayer().getWeapon().interactMonster(((MonsterView) element).getMonster());
        }
    }
}
