package com.lme.martianrobot.command;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommandRegistry {

    private final Map<Character, Command> registry = new HashMap<>();

    public CommandRegistry(LeftCommand leftCommand, RightCommand rightCommand, ForwardCommand forwardCommand) {
        registry.put('L', leftCommand);
        registry.put('R', rightCommand);
        registry.put('F', forwardCommand);
    }

    public Command getCommandFor(final char ch) {
        return registry.get(ch);
    }
}
