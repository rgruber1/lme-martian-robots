package com.lme.martianrobot.command;

import com.lme.martianrobot.grid.Grid;
import com.lme.martianrobot.grid.Position;
import com.lme.martianrobot.grid.Robot;

public class LeftCommand implements Command {

    @Override
    public void accept(final Grid grid, final Robot robot) {
        Position position = grid.getPositionFor(robot);
        grid.setRobotOrientation(robot, position.getOrientation().turnLeft90Degrees());
    }

}
