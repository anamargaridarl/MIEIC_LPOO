package com.lpoo_32.model;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void moveUp(){
        this.y--;
    }

    public void moveLeft(){
        this.x--;
    }

    public void moveDown(){
        this.y++;
    }

    public void moveRight() { this.x++; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
