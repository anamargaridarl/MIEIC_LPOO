package com.lpoo_32.model;

import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.HungerRestored;
import com.lpoo_32.exceptions.ThirstOVF;
import com.lpoo_32.exceptions.ThirstRestored;

enum NourishType{
    THIRST,
    HUNGER
}

public class NourishStatus extends Status {
    private final NourishType type;

    public NourishStatus(int value, NourishType type) {
        super(value);
        this.type = type;
    }

    @Override
    public void decreaseValue(int value) throws HungerOVF, ThirstOVF {
        if(this.value != 0){
            this.value -= value;
            if(this.value <= 0) {
                this.value = 0;
                if(this.type == NourishType.HUNGER)
                    throw new HungerOVF();
                else
                    throw new ThirstOVF();
            }
        }
    }

    @Override
    public void increaseValue(int value) throws HungerRestored, ThirstRestored {
        if(this.value == 0){
            super.increaseValue(value);
            if(this.type == NourishType.HUNGER)
                throw new HungerRestored();
            else
                throw new ThirstRestored();
        }
        super.increaseValue(20);
    }

}
