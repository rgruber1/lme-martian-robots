package com.lme.martianrobot.grid;

public enum Orientation {

    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');

    private static final Orientation[] values = values();

    private final char code;

    Orientation(final char code) {
        this.code = code;
    }

    public Orientation turnRight90Degrees() {
        return values[(this.ordinal() + 1) % values.length];
    }

    public Orientation turnLeft90Degrees() {
        return values[this.ordinal() - 1 >= 0 ? this.ordinal() - 1 : values().length - 1];
    }

    public char getCode() {
        return code;
    }
}
