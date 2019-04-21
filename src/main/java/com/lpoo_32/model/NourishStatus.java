package com.lpoo_32.model;

import com.lpoo_32.exceptions.NourishOVF;
import com.lpoo_32.exceptions.NourishRestored;

public class NourishStatus extends Status {
    private boolean overflow;
    public NourishStatus(int value) {
        super(value);
        this.overflow = false;
    }

    @Override
    public void decreaseValue(int value) throws NourishOVF {
        this.value -= value;
        if(this.value <= 0) {
            value = 0;
            this.overflow = true;
            throw new NourishOVF();
        }
    }

    @Override
    public void increaseValue(int value) throws NourishRestored {
        super.increaseValue(value);
        if(this.overflow){
            throw new NourishRestored();
        }
    }

}
