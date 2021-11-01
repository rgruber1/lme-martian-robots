package com.lme.martianrobot.grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CoordinatesTest {

    @Test
    void testEquals() {
        assertEquals(new Coordinates(0, 0), new Coordinates(0, 0));
        assertNotEquals(new Coordinates(0, 0), new Coordinates(0, 1));
        assertNotEquals(new Coordinates(0, 0), new Coordinates(1, 0));
    }

}