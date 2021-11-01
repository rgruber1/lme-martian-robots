package com.lme.martianrobot;

import com.lme.martianrobot.command.Command;
import com.lme.martianrobot.command.CommandRegistry;
import com.lme.martianrobot.grid.Coordinates;
import com.lme.martianrobot.grid.Orientation;
import com.lme.martianrobot.grid.Position;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

@Service
public class CommandParser {

    private static final Pattern coordinatesPattern = Pattern.compile("\\d{1,5}\\s\\d{1,5}");
    private static final Pattern positionPattern = Pattern.compile("\\d{1,5}\\s\\d{1,5}\\s[NSEW]");
    private static final Pattern commandPattern = Pattern.compile("[LRF]{1,100}");

    private final CommandRegistry commandRegistry;

    public CommandParser(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    public boolean isCoordinates(String line) {
        return matches(coordinatesPattern, line);
    }

    public boolean isPosition(String line) {
        return matches(positionPattern, line);
    }

    public boolean isInstruction(String line) {
        return matches(commandPattern, line);
    }

    private boolean matches(Pattern pattern, String line) {
        if (line == null) {
            return false;
        }
        return pattern.matcher(line).matches();
    }

    public Coordinates parseCoordinates(final String next) {
        if (!isCoordinates(next)) {
            throw new RuntimeException("Invalid parameter - expected coordinates [x y]");
        }

        String[] gridCoords = next.trim().split(" ");
        return new Coordinates(parseInt(gridCoords[0]), parseInt(gridCoords[1]));
    }

    public Position parsePosition(final String line) {
        if (!isPosition(line)) {
            throw new RuntimeException("Invalid parameter - expected position line [x y [NSEW]]");
        }

        String[] component = line.split(" ");

        String x = component[0];
        String y = component[1];
        String orientationString = component[2];

        Orientation orientation = Orientation.lookupByCode(orientationString.charAt(0));
        return new Position(new Coordinates(Integer.parseInt(x), Integer.parseInt(y)),
                orientation);
    }

    public List<Command> parseInstructions(String moveInstructions) {
        if (!isInstruction(moveInstructions)) {
            return Collections.emptyList();
        }
        List<Command> result = new ArrayList<>();
        for (char ch : moveInstructions.toCharArray()) {
            result.add(commandRegistry.getCommandFor(ch));
        }
        return result;
    }

}
