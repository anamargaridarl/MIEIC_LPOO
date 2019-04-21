package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.NourishOVF;
import com.lpoo_32.exceptions.NourishRestored;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class StatusTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void healthTest() throws HealthOVF, NourishOVF, NourishRestored {
        Status health = new HealthStatus(100);
        health.decreaseValue(20);
        assertEquals(80, health.getValue());
        health.increaseValue(20);
        assertEquals(100, health.getValue());
        thrown.expect(HealthOVF.class);
        health.decreaseValue(200);
    }

    @Test
    public void nourishTest() throws HealthOVF, NourishOVF, NourishRestored{
        Status nourishment = new NourishStatus(100);
        nourishment.decreaseValue(20);
        assertEquals(80, nourishment.getValue());
        nourishment.increaseValue(20);
        assertEquals(100, nourishment.getValue());
        thrown.expect(NourishOVF.class);
        nourishment.decreaseValue(200);
        thrown.expect(NourishRestored.class);
        nourishment.increaseValue(20);
    }


}