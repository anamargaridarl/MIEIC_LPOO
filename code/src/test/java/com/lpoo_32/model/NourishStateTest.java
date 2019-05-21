package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ThirstOVF;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class NourishStateTest {
    private PlayerModel player;
    private Status status;
    @Before
    public void initializeValues(){
        player = Mockito.mock(PlayerModel.class);
        status = Mockito.mock(Status.class);
        Mockito.when(player.getWater()).thenReturn(status);
        Mockito.when(player.getHealth()).thenReturn(status);
        Mockito.when(player.getFood()).thenReturn(status);
    }

    @Test
    public void sated() throws HungerOVF, HealthOVF, ThirstOVF {
        NourishState sated = new SatedState(player);
        sated.update(5400);
        verify(status).decreaseValue(5);
    }

    @Test
    public void quenched() throws HungerOVF, HealthOVF, ThirstOVF {
        NourishState quenched = new QuenchedState(player);
        quenched.update(3600);
        verify(status).decreaseValue(5);
    }

    @Test
    public void famish() throws HungerOVF, HealthOVF, ThirstOVF {
        NourishState famish = new FamishState(player);
        famish.update(120);
        verify(status).decreaseValue(5);
    }
}