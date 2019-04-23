package com.lpoo_32.view;

import com.lpoo_32.model.HealthStatus;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatusBarTest {
    @Test
    public void screenPercentage(){
        StatusBar bar = new StatusBar(new HealthStatus(100), "#000000", 10);
        assertEquals(40, bar.getScreenPercen());
        bar = new StatusBar(new HealthStatus(80), "#000000", 10);
        assertEquals(32, bar.getScreenPercen());
        bar = new StatusBar(new HealthStatus(0), "#000000", 10);
        assertEquals(0, bar.getScreenPercen());
    }

}