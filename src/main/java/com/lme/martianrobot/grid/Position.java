package com.lme.martianrobot.grid;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Position {

    private Coordinates coordinates;
    private Orientation orientation;

}
