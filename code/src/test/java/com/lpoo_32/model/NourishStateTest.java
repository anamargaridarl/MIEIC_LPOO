package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ThirstOVF;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class NourishStateTest {

    @Test
    public void update() throws HungerOVF, HealthOVF, ThirstOVF {
        PlayerModel player = Mockito.mock(PlayerModel.class);
        Status status = Mockito.mock(Status.class);
        Mockito.when(player.getWater()).thenReturn(status);
        Mockito.when(player.getHealth()).thenReturn(status);
        Mockito.when(player.getFood()).thenReturn(status);

        NourishState sated = new SatedState(player);
        sated.update(5400);
        verify(status).decreaseValue(5);


        NourishState quenched = new QuenchedState(player);
        sated.update(3600);
        verify(status).decreaseValue(5);


        NourishState famish = new FamishState(player);
        sated.update(120);
        verify(status).decreaseValue(5);


    }
}