package com.lpoo_32.model;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.view.DisplayProps;
import com.lpoo_32.view.Game;
import com.lpoo_32.view.TerminalKeyboard;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class WeaponTest {

    @Test
    public void interact() throws OutOfBoundaries {
        PlayerModel player = new PlayerModel(new Position(4,5, Game.width/4, Game.height/4, 0));
        Screen screen = Mockito.mock(Screen.class);
        GameController gameController = new GameController(new Elements(),player, Mockito.mock(Game.class));
        MonsterModel monster = new MonsterModel(new Position(5,5,Game.width/4, Game.height/4, 0),40,new MovableElement(player.getPosition(), ""), gameController,player.getPosition());
        WeaponModel weapon = new WeaponModel(player.getPosition(),2);

        weapon.interactMonster(monster);
        assertEquals(48, monster.getHealth());

    }




}
