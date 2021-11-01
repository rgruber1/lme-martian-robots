package com.lme.martianrobot;

import com.lme.martianrobot.command.Command;
import com.lme.martianrobot.grid.Coordinates;
import com.lme.martianrobot.grid.Grid;
import com.lme.martianrobot.grid.Position;
import com.lme.martianrobot.grid.Robot;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class GridController {

    private Grid grid;
    private Robot currentRobot;

    public void setCoordinates(Coordinates coordinates) {
        this.grid = new Grid(coordinates);
    }

    public boolean hasGrid() {
        return grid != null;
    }

    public void setNewRobotPosition(Position position) {
        currentRobot = new Robot();
        grid.addRobot(currentRobot, position);
    }

    public void moveCurrentRobot(Command[] moveInstructions) {
        for (Command command : moveInstructions) {
            command.accept(grid, currentRobot);
        }
//        Arrays.stream(moveInstructions).forEach(command -> command.accept(grid, currentRobot));
    }

    public String describeCurrentRobotPosition() {
        Position robotPosition = grid.getPositionFor(currentRobot);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(robotPosition.getCoordinates().getX()).append(" ").append(
                        robotPosition.getCoordinates().getY()).append(" ")
                .append(robotPosition.getOrientation().getCode());

        if (grid.isLost(currentRobot)) {
            stringBuilder.append(" LOST");
        }

        return stringBuilder.toString();
    }
}
