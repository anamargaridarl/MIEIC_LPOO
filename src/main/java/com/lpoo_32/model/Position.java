package com.lpoo_32.model;

import com.googlecode.lanterna.TerminalPosition;
import com.lpoo_32.exceptions.DownScreen;
import com.lpoo_32.exceptions.LeftScreen;
import com.lpoo_32.exceptions.RightScreen;
import com.lpoo_32.exceptions.UpScreen;
import com.lpoo_32.view.ScreenSize;

import java.util.Objects;

public class Position {
    private int x;
    private int y;
    private int heightIndex;
    private int widthIndex;
    private final int width;
    private final int height;

    public Position(int x, int y, int width, int height, int index){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.heightIndex = index/3;
        this.widthIndex = index%3;
    }

    public void moveUp() throws UpScreen {

        if(this.y - 1 > this.heightIndex * height)
            this.y --;
        else
            throw new UpScreen();
    }

    public void moveLeft() throws LeftScreen {

        if(this.x - 1 > this.widthIndex * width)
            this.x--;
        else
            throw new LeftScreen();
    }

    public void moveDown() throws DownScreen {

        if(this.y + 1 <= this.height * (heightIndex + 1))
            this.y++;
        else
            throw new DownScreen();
    }

    public void moveRight() throws RightScreen {

        if(this.x + 1 <= this.width * (widthIndex + 1))
            this.x++;
        else
            throw new RightScreen();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TerminalPosition getTerminalPosition(){
        int x = this.x - (this.width * this.widthIndex);
        int y = this.y - (this.height * this.heightIndex);
        return new TerminalPosition(ScreenSize.instance().getColumn(x * 2 - 1),
                                    ScreenSize.instance().getRows(y * 2 - 1)
                                    );
    }

    public void setIndex(int index){
        this.heightIndex = index/3;
        this.widthIndex = index%3;
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
