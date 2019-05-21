package com.lpoo_32.view.swing;

import com.lpoo_32.model.FoodModel;

import javax.swing.*;
import java.awt.*;

public class FoodViewSwing extends SwingComponent {
    private FoodModel food;
    public FoodViewSwing(FoodModel food){
        this.food = food;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics, "stickman.png");
    }

}

