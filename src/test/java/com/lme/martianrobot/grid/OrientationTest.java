package com.lme.martianrobot.grid;

import org.junit.jupiter.api.Test;

import static com.lme.martianrobot.grid.Orientation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrientationTest {

    @Test
    public void turnRight90Degrees() {
        assertEquals(EAST, NORTH.turnRight90Degrees());
        assertEquals(SOUTH, EAST.turnRight90Degrees());
        assertEquals(WEST, SOUTH.turnRight90Degrees());
        assertEquals(NORTH, WEST.turnRight90Degrees());
    }

    @Test
    public void turnLeft90Degrees() {
        assertEquals(WEST, NORTH.turnLeft90Degrees());
        assertEquals(SOUTH, WEST.turnLeft90Degrees());
        assertEquals(EAST, SOUTH.turnLeft90Degrees());
        assertEquals(NORTH, EAST.turnLeft90Degrees());
    }
}