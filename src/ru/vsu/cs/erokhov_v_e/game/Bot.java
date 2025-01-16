package ru.vsu.cs.erokhov_v_e.game;

import java.util.List;
import java.util.Set;

public class Bot implements Strategy {

    @Override
    public void placeFox(Fox fox, GameField gameField) {
        Set<Coordinate> available = gameField.getGameFieldMap().keySet();
    }

    @Override
    public void moveFox(Fox fox, List<Goose> geese, GameField gameField) {

    }

    @Override
    public void moveGoose(List<Goose> geese, GameField gameField) {

    }
}
