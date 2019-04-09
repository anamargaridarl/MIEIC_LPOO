package com.lpoo_32.logic;

import com.lpoo_32.exceptions.StatusOverflow;

public class Status {
    private int value;
    Status(int value){
        this.value = value;
    }

    public void decreaseValue(int value) throws StatusOverflow {
        if(this.value -  value <= 0)
            throw new StatusOverflow();
        this.value -= value;
    }

    public void increaseValue(int value) {
        if(this.value + value > 100){
            this.value = 100;
            return;
        }
        this.value += value;
    }
}
