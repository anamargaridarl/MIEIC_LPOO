package com.lpoo_32.model;

import com.lpoo_32.exceptions.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class NourishStateTest {
    private PlayerModel player;
    private Status status;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Before
    public void initializeValues(){
        player = Mockito.mock(PlayerModel.class);
        status = Mockito.mock(Status.class);
        Mockito.when(player.getWater()).thenReturn(status);
        Mockito.when(player.getHealth()).thenReturn(status);
        Mockito.when(player.getFood()).thenReturn(status);
    }

    @Test
    public void sated() throws HungerOVF, HealthOVF, ThirstOVF, HungerRestored, ThirstRestored, Sleeptime {
        NourishState sated = new SatedState(player);
        sated.update(5400);
        verify(status).decreaseValue(5);
    }

    @Test
    public void quenched() throws HungerOVF, HealthOVF, ThirstOVF, HungerRestored, ThirstRestored, Sleeptime {
        NourishState quenched = new QuenchedState(player);
        quenched.update(3600);
        verify(status).decreaseValue(5);
    }

    @Test
    public void famish() throws HungerOVF, HealthOVF, ThirstOVF, HungerRestored, ThirstRestored, Sleeptime {
        NourishState famish = new FamishState(player);
        famish.update(120);
        verify(status).decreaseValue(5);
    }

    @Test
    public void sleep() throws HungerOVF, ThirstOVF, HealthOVF, ThirstRestored, Sleeptime, HungerRestored {
        NourishState sleep =  new SleepState(player);
        thrown.expect(Sleeptime.class);
        sleep.update(0);
    }

    @Test

    public void day() throws HungerOVF, ThirstOVF, HealthOVF, ThirstRestored, Sleeptime, HungerRestored {
        NourishState day = new DayState(player);
        day.update(0);
        verify(status, never()).decreaseValue(any(int.class));
        verify(status, never()).increaseValue(any(int.class));
    }
}