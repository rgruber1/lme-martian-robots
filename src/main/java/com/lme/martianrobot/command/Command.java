package com.lme.martianrobot.command;

import com.lme.martianrobot.grid.Grid;
import com.lme.martianrobot.grid.Robot;

import java.util.function.BiConsumer;

public interface Command extends BiConsumer<Grid, Robot> {
}
