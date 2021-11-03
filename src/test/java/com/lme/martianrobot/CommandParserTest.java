package com.lme.martianrobot;

import com.lme.martianrobot.command.ForwardCommand;
import com.lme.martianrobot.command.LeftCommand;
import com.lme.martianrobot.command.RightCommand;
import com.lme.martianrobot.grid.Coordinates;
import com.lme.martianrobot.grid.Orientation;
import com.lme.martianrobot.grid.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class CommandParserTest {

    @Autowired
    private CommandParser commandParser;

    @Autowired
    private LeftCommand leftCommand;

    @Autowired
    private RightCommand rightCommand;

    @Autowired
    private ForwardCommand forwardCommand;

    @Test
    void testIsCoordinates() {
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
    void testIsPosition() {
        assertFalse(commandParser.isPosition(null));
        assertFalse(commandParser.isPosition(""));
        assertFalse(commandParser.isPosition("A B"));
        assertFalse(commandParser.isPosition("1 1"));

        assertTrue(commandParser.isPosition("1 1 N"));
        assertTrue(commandParser.isPosition("1 1 S"));
        assertTrue(commandParser.isPosition("1 1 E"));
        assertTrue(commandParser.isPosition("1 1 W"));
        assertTrue(commandParser.isPosition("10 10 W"));

        assertFalse(commandParser.isPosition("10 10 WN"));
        assertFalse(commandParser.isPosition("1 1 X"));
    }

    @Test
    void testParsePosition() {
        assertEquals(new Position(new Coordinates(1, 1), Orientation.EAST), commandParser.parsePosition("1 1 E"));
        assertEquals(new Position(new Coordinates(3, 2), Orientation.NORTH), commandParser.parsePosition("3 2 N"));
        assertEquals(new Position(new Coordinates(0, 3), Orientation.WEST), commandParser.parsePosition("0 3 W"));
    }

    @Test
    void testIsCommand() {
        assertFalse(commandParser.isInstruction(null));
        assertFalse(commandParser.isInstruction(""));
        assertFalse(commandParser.isInstruction("A"));
        assertTrue(commandParser.isInstruction("L"));
        assertTrue(commandParser.isInstruction("R"));
        assertTrue(commandParser.isInstruction("F"));
        assertTrue(commandParser.isInstruction("LRF"));
        assertTrue(commandParser.isInstruction("LRF".repeat(33)));
    }

    @Test
    void testParseInstructions() {
        assertEquals(Collections.EMPTY_LIST, commandParser.parseInstructions(null));
        assertEquals(Collections.EMPTY_LIST, commandParser.parseInstructions(""));
        assertEquals(Collections.EMPTY_LIST, commandParser.parseInstructions("A"));
        assertEquals(List.of(leftCommand), (commandParser.parseInstructions("L")));
        assertEquals(List.of(rightCommand), commandParser.parseInstructions("R"));
        assertEquals(List.of(forwardCommand), commandParser.parseInstructions("F"));
        assertEquals(List.of(leftCommand, rightCommand, forwardCommand), commandParser.parseInstructions("LRF"));
    }

}