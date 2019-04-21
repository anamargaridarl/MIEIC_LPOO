package com.lpoo_32.model;

import com.lpoo_32.exceptions.*;

public abstract class Status {
    int value;
    public Status(int value){
        this.value = value;
    }

    public abstract void decreaseValue(int value) throws HealthOVF, HungerOVF, ThirstOVF;

    public void increaseValue(int value) throws HungerRestored, ThirstRestored {
        if(this.value + value > 100)
            this.value = 100;
        else
            this.value += value;
    }

    public int getValue(){
        return this.value;
    }
}
