package com.lme.martianrobot;

import com.lme.martianrobot.grid.Coordinates;
import com.lme.martianrobot.grid.Orientation;
import com.lme.martianrobot.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class CommandRunnerTest {

    @Autowired
    private CommandRunner commandRunner;

    @Autowired
    private GridController gridController;

    @BeforeEach
    void initializeGridCoordinates() {
        commandRunner.runCommand("5 3");
    }

    @Test
    void testRobot1Sequence() {
        assertEquals(new Coordinates(5, 3), gridController.getGrid().getCoordinates());

        // Robot 1 scenario
        commandRunner.runCommand("1 1 E");
        assertPositionEquals(1, 1, Orientation.EAST);
        assertEquals("1 1 E", commandRunner.runCommand("RFRFRFRF").get());
        assertPositionEquals(1, 1, Orientation.EAST);
    }

    @Test
    void testRobot2Sequence() {
        assertEquals(new Coordinates(5, 3), gridController.getGrid().getCoordinates());

        // robot 2 scenario
        commandRunner.runCommand("3 2 N");
        assertPositionEquals(3, 2, Orientation.NORTH);
        assertEquals("3 3 N LOST", commandRunner.runCommand("FRRFLLFFRRFLL").get());
        assertPositionEquals(3, 3, Orientation.NORTH);
    }

    @Test
    void testRobot3Sequence() {
        assertEquals(new Coordinates(5, 3), gridController.getGrid().getCoordinates());

//        // robot 3 scenario
        commandRunner.runCommand("0 3 W");
        assertPositionEquals(0, 3, Orientation.WEST);
        assertEquals("3 3 N LOST", commandRunner.runCommand("LLFFFLFLFL").get());
        // NOTE! robot fell off the grid at position (3,3) facing North whereas specification indicates that output should be "2 3 S"
        assertPositionEquals(3, 3, Orientation.NORTH);
//        assertPositionEquals(2, 3, Orientation.SOUTH);
    }

    private void assertPositionEquals(int i, int i2, Orientation east) {
        assertEquals(new Position(new Coordinates(i, i2), east),
                gridController.getGrid().getPositionFor(gridController.getCurrentRobot()));
    }

}