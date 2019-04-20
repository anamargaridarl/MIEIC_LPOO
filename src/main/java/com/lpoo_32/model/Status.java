package com.lpoo_32.model;

import com.lpoo_32.exceptions.StatusOverflow;

public class Status {
    private int value;
    public Status(int value){
        this.value = value;
    }

    public void decreaseValue(int value) throws StatusOverflow {
        this.value -= value;
        if(this.value <= 0) {
            throw new StatusOverflow();
        }
    }

    public void increaseValue(int value) {
        if(this.value + value > 100)
            this.value = 100;
        else
            this.value += value;
    }

    public int getValue(){
        return this.value;
    }
}
