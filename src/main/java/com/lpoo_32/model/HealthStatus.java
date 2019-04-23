package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;

public class HealthStatus extends Status {
    public HealthStatus(int value) {
        super(value);
    }

    @Override
    public void decreaseValue(int value) throws HealthOVF {
        this.value -= value;
        if(this.value <= 0) {
            throw new HealthOVF();
        }
    }
}
