package com.lme.martianrobot.grid;

import java.util.*;

public class Grid {

    private final Coordinates coordinates;
    private final Map<Robot, Position> positions = new LinkedHashMap<>();
    private final Set<Robot> lostRobots = new HashSet<>();

    public Grid(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void addRobot(final Robot robot, final Position initialPosition) {
        this.positions.put(robot, initialPosition);
    }

    public Position getPositionFor(final Robot robot) {
        return this.positions.get(robot);
    }

    public void setRobotOrientation(Robot robot, Orientation orientation) {
        if (lostRobots.contains(robot)) {
            return;
        }

        positions.put(robot, new Position(positions.get(robot).getCoordinates(), orientation));
    }

    public void moveRobotTo(final Robot robot, final Coordinates newCoordinates) {
        Position currentPosition = positions.get(robot);
        if (isLost(currentPosition.getCoordinates())) {
            return;
        }

        if (isLost(newCoordinates)) {
            lostRobots.add(robot);
        } else {
            positions.put(robot, new Position(newCoordinates, currentPosition.getOrientation()));
        }
    }

    private boolean isLost(final Coordinates robotCoordinates) {
        return robotCoordinates.getX() > coordinates.getX() || robotCoordinates.getY() > coordinates.getY();
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public boolean isLost(Robot robot) {
        return lostRobots.contains(robot);
    }

    public List<Robot> getRobots() {
        return new ArrayList<>(positions.keySet());
    }
}
