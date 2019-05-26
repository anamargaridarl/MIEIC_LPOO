package com.lpoo_32.view;

import com.lpoo_32.view.*;
import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

public class TerminalElementFactoryTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getElement() throws OutOfBoundaries {
        PlayerModel player = new PlayerModel(new Position(2,3,4,5,1));
        TerminalElementFactory factory = new TerminalElementFactory();
        ElementView water = factory.getElement(ElementType.WATER, null,null,player);
        assertTrue(water instanceof WaterView);
        ElementView food = factory.getElement(ElementType.FOOD, null,null,player);
        assertTrue(food instanceof FoodView);
        ElementView spikes = factory.getElement(ElementType.SPIKES, null,null,player);
        assertTrue(spikes instanceof SpikesView);
        thrown.expect(IllegalStateException.class);
        factory.getElement(ElementType.NONE, null,null,player);
    }
}