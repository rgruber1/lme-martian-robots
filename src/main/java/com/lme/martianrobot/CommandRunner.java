package com.lme.martianrobot;

import com.lme.martianrobot.grid.Coordinates;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class CommandRunner {

    private final CommandParser commandParser;

    private final GridController gridController;

    public Optional<String> runCommand(String string) {
        if (string == null) {
            return Optional.empty();
        }

        String line = string.trim().replaceAll(" +", " ");
        log.debug("Received line {}", line);

        if (line.length() > 100) {
            return Optional.of("Error: line length should not exceed 100 characters - ignoring sequence");
        }

        if ("STATUS".equals(line)) {
            for (String robotStatus : gridController.getRobotStatuses()) {
                System.out.println(robotStatus);
            }
        } else if ("QUIT".equals(line)) {
            System.exit(0);
        }

        if (commandParser.isCoordinates(line)) {
            Coordinates coordinates = commandParser.parseCoordinates(line);
            gridController.setCoordinates(coordinates);
        }

        if (!gridController.hasGrid()) {
            return Optional.of("Error: first input line should be the grid coordinates in the form [x y]");
        }

        if (commandParser.isPosition(line)) {
            gridController.setNewRobotPosition(commandParser.parsePosition(line));
        }

        if (commandParser.isInstruction(line)) {
            gridController.moveCurrentRobot(commandParser.parseInstructions(line));
            return Optional.of(gridController.describeCurrentRobotPosition());
        }

        return Optional.empty();
    }

}
