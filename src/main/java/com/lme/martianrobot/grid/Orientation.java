package com.lme.martianrobot.grid;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Orientation {

    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');

    private static final Orientation[] values = values();

    private final char code;

    public Orientation turnRight90Degrees() {
        return values[(this.ordinal() + 1) % values.length];
    }

    public Orientation turnLeft90Degrees() {
        return values[this.ordinal() - 1 >= 0 ? this.ordinal() - 1 : values().length - 1];
    }

    public static Orientation lookupByCode(final char character) {
        for (Orientation e : values()) {
            if (e.code == character) {
                return e;
            }
        }
        return null;
    }
}
