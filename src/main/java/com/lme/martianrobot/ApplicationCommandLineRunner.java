package com.lme.martianrobot;

import com.lme.martianrobot.grid.Coordinates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Profile("!test")
@Component
@Slf4j
public class ApplicationCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CommandParser commandParser;

    @Autowired
    private GridController gridController;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        log.info("Command line scanner started");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim().replaceAll(" +", " ");

            if (line.length() > 100) {
                System.out.println("Error: line length should not exceed 100 characters - ignoring sequence");
                continue;
            }

            if (commandParser.isCoordinates(line)) {
                Coordinates coordinates = commandParser.parseCoordinates(line);
                gridController.setCoordinates(coordinates);
            }

            if (commandParser.isPosition(line)) {
                gridController.addRobotPosition(commandParser.parsePosition(line));
            }

            if (!gridController.hasGrid()) {
                System.out.println("Error: Expected grid size [X Y]");
            }
        }
    }

}
