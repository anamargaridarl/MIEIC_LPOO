package com.lpoo_32.view;

import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.PlayerModel;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Game {
    public static final int width = 60;
    public static final int height = 50;
    protected int index;
    protected List<ElementView> generalView;
    protected PlayerView player;


    public Game(PlayerModel player) {
        this.player = new PlayerView(player);
        this.index = 0;
        this.generalView = new LinkedList<>();
    }

    abstract public void draw() throws IOException, HungerOVF, ThirstOVF, ThirstRestored, RightScreen, DownScreen, LeftScreen, HealthOVF, HungerRestored, UpScreen, Bedtime;

//    abstract public void destroy();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    abstract void setInitialProps();

}
