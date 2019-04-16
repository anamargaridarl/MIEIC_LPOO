package com.lpoo_32.model;

import com.lpoo_32.view.Game;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void moveUp(){

        if(this.y-- < 0)
            this.y++;
    }

    public void moveLeft(){

        if(this.x-- < 0)
            this.x++;


    }

    public void moveDown(){

        if(this.y++ > Game.height)
            this.y--;
    }

    public void moveRight() {

        if(this.x++ > Game.width)
            this.x--;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
