package com.lme.martianrobot;

import com.lme.martianrobot.grid.Coordinates;
import com.lme.martianrobot.grid.Grid;
import org.springframework.stereotype.Service;

@Service
public class GridController {

    private Grid grid;

    public void setCoordinates(Coordinates coordinates) {
        this.grid = new Grid(coordinates);
    }

    public boolean hasGrid() {
        return grid != null;
    }
}
