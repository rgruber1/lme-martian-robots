package com.lme.martianrobot;

import com.lme.martianrobot.grid.Coordinates;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class CommandParserTest {

    @Autowired
    private CommandParser commandParser;

    @Test
    void testIsCoordinatesCommand() {
        assertFalse(commandParser.isCoordinates(null));
        assertFalse(commandParser.isCoordinates(""));
        assertFalse(commandParser.isCoordinates("A B"));
        assertTrue(commandParser.isCoordinates("1 1"));
        assertTrue(commandParser.isCoordinates("10 10"));
        assertTrue(commandParser.isCoordinates("100 100"));
    }

    @Test
    void testParseGridCoords() {
        assertEquals(new Coordinates(1, 1), commandParser.parseGridCoords("1 1"));
        assertEquals(new Coordinates(10, 10), commandParser.parseGridCoords("10 10"));
        assertEquals(new Coordinates(100, 100), commandParser.parseGridCoords("100 100"));
    }

}