package com.lpoo_32.model;

import com.googlecode.lanterna.TerminalPosition;
import com.lpoo_32.view.Game;
import com.lpoo_32.view.ScreenSize;

import java.util.Objects;

public class Position {
    private int x;
    private int y;
    private final int width;
    private final int height;

    public Position(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void moveUp(){

        if(this.y - 1 >= 0)
            this.y --;
    }

    public void moveLeft(){

        if(this.x - 1 >= 0)
            this.x--;
    }

    public void moveDown(){

        if(this.y + 1 <= this.height)
            this.y++;
    }

    public void moveRight() {

        if(this.x + 1 <= this.width)
            this.x++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TerminalPosition getTerminalPosition(){
        return new TerminalPosition(ScreenSize.instance().getColumn(this.x * 2 - 1),
                                    ScreenSize.instance().getRows(this.y * 2 - 1)
                                    );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof Position))
            return false;

        Position pos = (Position) obj;
        return this.getX() == pos.getX() && this.getY() == pos.getY();
    }
}
