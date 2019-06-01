package com.lpoo_32.controller.action;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.view.InteractableElementView;
import com.lpoo_32.view.MonsterView;

public class MoveUp implements ActionEvent {
    private final GameController controller;

    public MoveUp(GameController controller){
        this.controller = controller;
    }
    @Override
    public void execute() throws UpScreen, HungerOVF, Bedtime, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored {
        InteractableElementView i;
        PlayerModel player = controller.getPlayer();
        i = controller.getElements().getView(player.getPosition().checkMovementUp());
        if(i !=  null) {
            if (i instanceof MonsterView)
                return;
        }
        if(this.controller.collisions(player.getPosition().checkMovementUp()))
            player.moveUp();
    }
}
