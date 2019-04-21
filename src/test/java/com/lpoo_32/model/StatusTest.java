package com.lpoo_32.model;

import com.lpoo_32.exceptions.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class StatusTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void healthTest() throws HealthOVF, HungerOVF, HungerRestored, ThirstOVF, ThirstRestored {
        Status health = new HealthStatus(100);
        health.decreaseValue(20);
        assertEquals(80, health.getValue());
        health.increaseValue(20);
        assertEquals(100, health.getValue());
        thrown.expect(HealthOVF.class);
        health.decreaseValue(200);
    }

    @Test
    public void hungerTest() throws HealthOVF, HungerOVF, HungerRestored, ThirstOVF, ThirstRestored {
        Status nourishment = new NourishStatus(100, NourishType.HUNGER);
        nourishment.decreaseValue(20);
        assertEquals(80, nourishment.getValue());
        nourishment.increaseValue(20);
        assertEquals(100, nourishment.getValue());
        thrown.expect(HungerOVF.class);
        nourishment.decreaseValue(200);
        thrown.expect(HungerRestored.class);
        nourishment.increaseValue(20);
    }

    @Test
    public void thirstTest() throws HealthOVF, HungerOVF, HungerRestored, ThirstOVF, ThirstRestored {
        Status nourishment = new NourishStatus(100, NourishType.THIRST);
        nourishment.decreaseValue(20);
        assertEquals(80, nourishment.getValue());
        nourishment.increaseValue(20);
        assertEquals(100, nourishment.getValue());
        thrown.expect(ThirstOVF.class);
        nourishment.decreaseValue(200);
        thrown.expect(ThirstRestored.class);
        nourishment.increaseValue(20);
    }


}