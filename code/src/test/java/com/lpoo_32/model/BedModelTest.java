package com.lpoo_32.model;

import com.lpoo_32.exceptions.Bedtime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BedModelTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    @Test
    public void interact() throws Bedtime {
        BedModel bed = new BedModel(null);
        thrown.expect(Bedtime.class);
        bed.interact(null);
    }

}