package com.lpoo_32.controller.action;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.view.InteractableElementView;
import com.lpoo_32.view.MonsterView;

public class MoveDown implements ActionEvent {
    private final GameController controller;

    public MoveDown(GameController controller){
        this.controller = controller;
    }
    @Override
    public void execute() throws HungerOVF, Bedtime, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, DownScreen {
        InteractableElementView i;
        PlayerModel player = controller.getPlayer();
        i = controller.getElements().getView(player.getPosition().checkMovementDown());
        if(i !=  null) {
            if (i instanceof MonsterView)
                return;
        }
        if(this.controller.collisions(player.getPosition().checkMovementDown()))
            player.moveDown();
    }
}
