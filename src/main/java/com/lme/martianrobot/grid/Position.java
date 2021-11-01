package com.lme.martianrobot.grid;

import java.util.Objects;

public class Position {

    private Coordinates coordinates;
    private Orientation orientation;

    public Position(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Orientation getOrientation() {
        return orientation;
    }


    public String getShortDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getCoordinates().getX()).append(" ").append(getCoordinates().getY()).append(" ")
                .append(getOrientation().getCode());
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return coordinates.equals(position.coordinates) && orientation == position.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, orientation);
    }

    @Override
    public String toString() {
        return "Position{" +
                "coordinates=" + coordinates +
                ", orientation=" + orientation +
                '}';
    }
}
