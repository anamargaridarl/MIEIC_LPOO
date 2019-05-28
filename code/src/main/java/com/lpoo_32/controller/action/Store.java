package com.lpoo_32.controller.action;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.view.CatchableView;

public class Store implements ActionEvent {
    private final GameController controller;

    public Store(GameController controller){
        this.controller = controller;
    }
    @Override
    public void execute() {
        PlayerModel player = this.controller.getPlayer();
        if (controller.isCatchable(player.getPosition())) {
            player.addElementInventory((CatchableView) controller.getElements().getView(player.getPosition()));
            controller.removeElementProps(controller.getElements().getModel(player.getPosition()));
        }
    }
}
