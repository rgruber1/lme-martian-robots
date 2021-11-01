package com.lme.martianrobot.grid;

import java.util.LinkedHashMap;
import java.util.Map;

public class Grid {

    private Coordinates coordinates;
    private Map<Robot, Position> positions = new LinkedHashMap<>();

    public Grid(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void addRobot(final Robot robot, final Position initialPosition) {
        this.positions.put(robot, initialPosition);
    }

}
