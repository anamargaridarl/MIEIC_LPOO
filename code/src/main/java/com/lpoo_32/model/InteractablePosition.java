package com.lpoo_32.model;


import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.exceptions.UpScreen;

public class InteractablePosition extends Position{

    int indexGame;

    public InteractablePosition(int x, int y, int width, int height, int index, int indexGame) throws OutOfBoundaries {
        super(x, y, width, height, index);
        this.indexGame = indexGame;
    }

    public void moveUp() {

        if (this.y - 1 >= this.heightIndex() * height)
            this.y--;
        else
            this.setIndex(indexGame -3 );


    }

    public void moveLeft() {

        if (this.x - 1 >= this.widthIndex() * width)
            this.x--;
        else
            this.setIndex(indexGame - 1 );
    }

    public void moveDown()  {

        if (this.y + 1 <= this.height * (heightIndex() + 1))
            this.y++;
        else
            this.setIndex(indexGame + 3 );
    }

    public void moveRight() {

        if (this.x + 1 <= this.width * (widthIndex() + 1))
            this.x++;
        else
            this.setIndex(indexGame + 1);
    }

}
