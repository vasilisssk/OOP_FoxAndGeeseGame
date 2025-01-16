package ru.vsu.cs.erokhov_v_e.game;

import java.util.List;

public interface Strategy {
    void placeFox(Fox fox, GameField gameField);
    void moveFox(Fox fox, List<Goose> geese, GameField gameField);
    void moveGoose(List<Goose> geese, GameField gameField);
}
