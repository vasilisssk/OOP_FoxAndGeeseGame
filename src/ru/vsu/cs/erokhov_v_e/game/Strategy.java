package ru.vsu.cs.erokhov_v_e.game;

import java.util.List;

public interface Strategy {
    void placeFox(Fox fox, GetNode getNodeInfo);
    boolean moveFox(Fox fox, List<Goose> geese, CalculateMoves calculateMoves);
    void moveGoose(List<Goose> geese, CalculateMoves calculateMoves);
}