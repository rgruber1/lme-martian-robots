package com.lme.martianrobot;

import com.lme.martianrobot.grid.Coordinates;
import com.lme.martianrobot.grid.Grid;
import com.lme.martianrobot.grid.Position;
import com.lme.martianrobot.grid.Robot;
import org.springframework.stereotype.Service;

@Service
public class GridController {

    private Grid grid;
    private Robot currentRobot;

    public void setCoordinates(Coordinates coordinates) {
        this.grid = new Grid(coordinates);
    }

    public boolean hasGrid() {
        return grid != null;
    }

    public void addRobotPosition(Position position) {
        currentRobot = new Robot();
        grid.addRobot(currentRobot, position);
    }
}
