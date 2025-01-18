package ru.vsu.cs.erokhov_v_e.game.xxx;

import ru.vsu.cs.erokhov_v_e.game.GameEntity;
import ru.vsu.cs.erokhov_v_e.game.Movement;

public interface MoveCalculator {
    public boolean calculate(GameEntity ge, Movement m);
}
