package com.lpoo_32.model;

import com.googlecode.lanterna.TerminalPosition;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.view.Game;
import com.lpoo_32.view.GameSwing;
import com.lpoo_32.view.ScreenSize;

import java.util.Objects;

public class Position {
    private int x;
    private int y;
    private int heightIndex;
    private int widthIndex;
    private final int width;
    private final int height;

    public Position(int x, int y, int width, int height, int index) throws OutOfBoundaries {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if(width * 4 > Game.width || height > Game.height || width < 0 || height < 0)
            throw new OutOfBoundaries();
        this.heightIndex = index/3;
        this.widthIndex = index%3;
    }

    private Position(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 0;
        this.height = 0;
    }

    public void moveUp() throws UpScreen {

        if(this.y - 1 >= this.heightIndex * height)
            this.y --;
        else
            throw new UpScreen();
    }

    public void moveLeft() throws LeftScreen {

        if(this.x - 1 >= this.widthIndex * width)
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

    public Position checkMovementUp() {
        return new Position(this.getX(), this.getY()- 1);
    }

    public Position checkMovementDown() {
        return new Position(this.getX(), this.getY()+ 1);
    }

    public Position checkMovementRight() {
        return new Position(this.getX() + 1, this.getY());
    }

    public Position checkMovementLeft() {
        return new Position(this.getX() - 1, this.getY());
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
        return new TerminalPosition(ScreenSize.instance().getColumn(x * 4 - 1),
                                    ScreenSize.instance().getRows(y * 4 - 1)
                                    );
    }

    public int getSwingX(){
        int x = this.x - (this.width * this.widthIndex);
        return (x * 4)*(GameSwing.ScreenWidth)/100;
    }

    public int getSwingY(){
        int y = this.y - (this.height * this.heightIndex);

        return (y * 4 )*(GameSwing.ScreenHeight)/100;
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
