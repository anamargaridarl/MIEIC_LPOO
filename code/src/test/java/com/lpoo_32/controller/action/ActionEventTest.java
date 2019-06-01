package com.lpoo_32.controller.action;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;
import com.lpoo_32.model.WeaponModel;
import com.lpoo_32.view.InteractableElementView;
import com.lpoo_32.view.MonsterView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ActionEventTest {
    private GameController game;
    private WeaponModel weapon;
    private MonsterView monster;
    private PlayerModel player;
    @Before
    public void initializeTests(){
        game = Mockito.mock(GameController.class);
        weapon = Mockito.mock(WeaponModel.class);
        player = Mockito.mock(PlayerModel.class);
        Elements elements = Mockito.mock(Elements.class);
        monster = Mockito.mock(MonsterView.class);
        Mockito.when(game.getPlayer()).thenReturn(player);
        Mockito.when(player.getWeapon()).thenReturn(weapon);
        Mockito.when(game.getElements()).thenReturn(elements);
        Mockito.when(player.getPosition()).thenReturn(Mockito.mock(Position.class));
        Mockito.when(elements.getView(isNull())).thenReturn(monster);
    }

    @Test
    public void attackUp() throws RightScreen, UpScreen, ThirstOVF, ThirstRestored, HealthOVF, DownScreen, LeftScreen, Bedtime, HungerOVF, HungerRestored, ScreenClose {
        ActionEvent event = new AttackUp(game);
        event.execute();
        verify(weapon).interactMonster(monster.getMonster());
    }


    @Test
    public void attackDown() throws RightScreen, UpScreen, ThirstOVF, ThirstRestored, HealthOVF, DownScreen, LeftScreen, Bedtime, HungerOVF, HungerRestored, ScreenClose {
        ActionEvent event = new AttackDown(game);
        event.execute();
        verify(weapon).interactMonster(monster.getMonster());
    }


    @Test
    public void attackLeft() throws RightScreen, UpScreen, ThirstOVF, ThirstRestored, HealthOVF, DownScreen, LeftScreen, Bedtime, HungerOVF, HungerRestored, ScreenClose {
        ActionEvent event = new AttackLeft(game);
        event.execute();
        verify(weapon).interactMonster(monster.getMonster());
    }


    @Test
    public void attackRight() throws RightScreen, UpScreen, ThirstOVF, ThirstRestored, HealthOVF, DownScreen, LeftScreen, Bedtime, HungerOVF, HungerRestored, ScreenClose {
        ActionEvent event = new AttackRight(game);
        event.execute();
        verify(weapon).interactMonster(monster.getMonster());
    }

    @Test
    public void noWeapon() throws RightScreen, UpScreen, ThirstOVF, ThirstRestored, HealthOVF, DownScreen, LeftScreen, Bedtime, HungerOVF, HungerRestored, ScreenClose {
        ActionEvent right = new AttackRight(game);
        ActionEvent left = new AttackLeft(game);
        ActionEvent up = new AttackRight(game);
        ActionEvent down = new AttackDown(game);
        Mockito.when(player.getWeapon()).thenReturn(null);
        right.execute();
        left.execute();
        up.execute();
        down.execute();
        verify(game, never()).getElements();
    }

}