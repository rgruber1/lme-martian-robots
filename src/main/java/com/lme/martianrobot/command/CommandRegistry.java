package com.lme.martianrobot.command;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommandRegistry {

    private final Map<Character, Command> registry = new HashMap<>();

    public CommandRegistry() {
        registry.put('L', new LeftCommand());
        registry.put('R', new RightCommand());
        registry.put('F', new ForwardCommand());
    }

    public Command getCommandFor(final char ch) {
        return registry.get(ch);
    }
}
