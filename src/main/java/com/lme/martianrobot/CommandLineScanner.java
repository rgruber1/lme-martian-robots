package com.lme.martianrobot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Profile("!test")
@Component
@Slf4j
@AllArgsConstructor
public class CommandLineScanner implements CommandLineRunner {

    private final CommandRunner commandRunner;

    @Override
    public void run(String... args) {
        log.info("Command line scanner started");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim().replaceAll(" +", " ");
            log.debug("Processing command {}", line);

            Optional<String> optionalOutput = commandRunner.runCommand(line);
            optionalOutput.ifPresent(s -> System.out.println(optionalOutput.get()));
        }
    }

}
