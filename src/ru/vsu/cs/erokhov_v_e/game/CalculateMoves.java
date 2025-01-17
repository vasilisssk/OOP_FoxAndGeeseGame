package ru.vsu.cs.erokhov_v_e.game;

import java.util.List;

public interface CalculateMoves {
    Boolean calculateFoxMove(Movement movement, Fox fox, List<Goose> geese);
    boolean calculateGooseMove(Movement movement, Goose goose);
}

