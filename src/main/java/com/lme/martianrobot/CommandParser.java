package com.lme.martianrobot;

import com.lme.martianrobot.grid.Coordinates;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

@Service
public class CommandParser {

    private static final Pattern coordinatesPattern = Pattern.compile("\\d{1,5}\\s\\d{1,5}");

    public boolean isCoordinates(String line) {
        if (line == null) {
            return false;
        }
        return coordinatesPattern.matcher(line).matches();
    }

    public Coordinates parseGridCoords(final String next) {
        if (!isCoordinates(next)) {
            throw new RuntimeException("Invalid parameter - expected coordinates [x y]");
        }

        String[] gridCoords = next.trim().split(" ");
        return new Coordinates(parseInt(gridCoords[0]), parseInt(gridCoords[1]));
    }
}
