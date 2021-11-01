package com.lme.martianrobot;

import com.lme.martianrobot.grid.Coordinates;
import com.lme.martianrobot.grid.Orientation;
import com.lme.martianrobot.grid.Position;
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
    void testParseCoordinates() {
        assertEquals(new Coordinates(1, 1), commandParser.parseCoordinates("1 1"));
        assertEquals(new Coordinates(10, 10), commandParser.parseCoordinates("10 10"));
        assertEquals(new Coordinates(100, 100), commandParser.parseCoordinates("100 100"));
    }

    @Test
    void testIsPositionCommand() {
        assertFalse(commandParser.isPosition(null));
        assertFalse(commandParser.isPosition(""));
        assertFalse(commandParser.isPosition("A B"));
        assertFalse(commandParser.isPosition("1 1"));

        assertTrue(commandParser.isPosition("1 1 N"));
        assertTrue(commandParser.isPosition("1 1 S"));
        assertTrue(commandParser.isPosition("1 1 E"));
        assertTrue(commandParser.isPosition("1 1 W"));
        assertTrue(commandParser.isPosition("10 10 W"));

        assertFalse(commandParser.isPosition("1 1 X"));
    }

    @Test
    void testParsePosition() {
        assertEquals(new Position(new Coordinates(1, 1), Orientation.EAST), commandParser.parsePosition("1 1 E"));
        assertEquals(new Position(new Coordinates(3, 2), Orientation.NORTH), commandParser.parsePosition("3 2 N"));
        assertEquals(new Position(new Coordinates(0, 3), Orientation.WEST), commandParser.parsePosition("0 3 W"));
    }


}