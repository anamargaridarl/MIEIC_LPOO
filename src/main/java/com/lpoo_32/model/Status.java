package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.NourishOVF;
import com.lpoo_32.exceptions.NourishRestored;

public abstract class Status {
    int value;
    public Status(int value){
        this.value = value;
    }

    public abstract void decreaseValue(int value) throws HealthOVF, NourishOVF;

    public void increaseValue(int value) throws NourishRestored {
        if(this.value + value > 100)
            this.value = 100;
        else
            this.value += value;
    }

    public int getValue(){
        return this.value;
    }
}
