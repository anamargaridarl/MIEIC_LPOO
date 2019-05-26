package com.lpoo_32.model;


import com.lpoo_32.exceptions.*;


public class PlayerPosition extends Position{


    public PlayerPosition(int x, int y, int width, int height, int index) throws OutOfBoundaries {
        super(x, y, width, height, index);
    }

    public void moveUp() throws UpScreen{

        if (this.y - 1 >= this.heightIndex() * height)
            this.y--;
        else
        throw new UpScreen();

    }

    public void moveLeft() throws LeftScreen {

        if (this.x - 1 >= this.widthIndex() * width)
            this.x--;
        else
            throw new LeftScreen();
    }

    public void moveDown() throws DownScreen {

        if (this.y + 1 <= this.height * (heightIndex() + 1))
            this.y++;
        else
            throw new DownScreen();
    }

    public void moveRight() throws RightScreen {

        if (this.x + 1 <= this.width * (widthIndex() + 1))
            this.x++;
        else
            throw new RightScreen();
    }


}