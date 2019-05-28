package com.lpoo_32.controller.action;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.view.InteractableElementView;
import com.lpoo_32.view.MonsterView;

public class MoveLeft implements ActionEvent {
    private final GameController controller;

    public MoveLeft(GameController controller){
        this.controller = controller;
    }

    @Override
    public void execute() throws HungerOVF, Bedtime, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, LeftScreen {
        InteractableElementView i;
        PlayerModel player = controller.getPlayer();
        i = controller.getElements().getView(player.getPosition().checkMovementLeft());
        if(i !=  null) {
            if (i instanceof MonsterView)
                return;
        }
        if(this.controller.collisions(player.getPosition().checkMovementLeft()))
            player.moveLeft();
    }

}
