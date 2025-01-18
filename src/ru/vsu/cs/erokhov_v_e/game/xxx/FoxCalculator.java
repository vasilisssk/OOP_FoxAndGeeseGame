package ru.vsu.cs.erokhov_v_e.game.xxx;

import ru.vsu.cs.erokhov_v_e.game.CalculateMoves;
import ru.vsu.cs.erokhov_v_e.game.GameEntity;
import ru.vsu.cs.erokhov_v_e.game.Goose;
import ru.vsu.cs.erokhov_v_e.game.Movement;

import java.util.List;

public class FoxCalculator implements MoveCalculator {
    private List<Goose> listOfGooses;

    public FoxCalculator(List<Goose> listOfGooses) {
        this.listOfGooses = listOfGooses;
    }

    @Override
    public boolean calculate(GameEntity ge, Movement m) {
        return false;
    }
}
