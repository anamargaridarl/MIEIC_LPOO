package com.lpoo_32.model;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.exceptions.ThirstOVF;
import com.lpoo_32.view.Game;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class WeaponTest {

    @Test
    public void interactMonster() throws OutOfBoundaries {
        PlayerModel player = new PlayerModel(new Position(4,5, Game.width/4, Game.height/4, 0));
        MonsterModel monster = new MonsterModel(new Position(5,5,Game.width/4, Game.height/4, 0),40,new MovableElement(player.getPosition(), ""), Mockito.mock(GameController.class),player.getPosition());
        WeaponModel weapon = new WeaponModel(player.getPosition(),2);
        weapon.interactMonster(monster);
        assertEquals(48, monster.getHealth());
    }

    @Test
    public void interactPlayer() throws HungerOVF, HealthOVF, ThirstOVF {
        PlayerModel player = Mockito.mock(PlayerModel.class);
        Status status = Mockito.mock(Status.class);
        Mockito.when(player.getHealth()).thenReturn(status);
        WeaponModel weapon = new WeaponModel(Mockito.mock(Position.class), 20);
        weapon.interact(player);
        verify(player).getHealth();
        verify(status).decreaseValue(any(int.class));
    }




}
