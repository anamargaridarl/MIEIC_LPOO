package com.lpoo_32.view;

import com.lpoo_32.model.Status;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatusBarTest {
    @Test
    public void screenPercentage(){
        StatusBar bar = new StatusBar(new Status(100), "#000000");
        assertEquals(40, bar.getScreenPercen());
        bar = new StatusBar(new Status(80), "#000000");
        assertEquals(32, bar.getScreenPercen());
        bar = new StatusBar(new Status(0), "#000000");
        assertEquals(0, bar.getScreenPercen());
    }

}