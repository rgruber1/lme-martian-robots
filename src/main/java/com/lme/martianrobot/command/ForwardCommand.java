package com.lme.martianrobot.command;

import com.lme.martianrobot.grid.Coordinates;
import com.lme.martianrobot.grid.Grid;
import com.lme.martianrobot.grid.Position;
import com.lme.martianrobot.grid.Robot;

public class ForwardCommand implements Command {

    @Override
    public void accept(final Grid grid, final Robot robot) {
        Position position = grid.getPositionFor(robot);
        grid.moveRobotTo(robot, getMoveForwardCoordinates(position));
    }

    static Coordinates getMoveForwardCoordinates(final Position position) {
        switch (position.getOrientation()) {
            case NORTH:
                return new Coordinates(position.getCoordinates().getX(), position.getCoordinates().getY() + 1);
            case EAST:
                return new Coordinates(position.getCoordinates().getX() + 1, position.getCoordinates().getY());
            case SOUTH:
                return new Coordinates(position.getCoordinates().getX(), position.getCoordinates().getY() - 1);
            case WEST:
                return new Coordinates(position.getCoordinates().getX() - 1, position.getCoordinates().getY());
            default:
                return position.getCoordinates();
        }
    }
}
