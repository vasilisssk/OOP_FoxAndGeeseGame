package ru.vsu.cs.erokhov_v_e.game;

public class Goose extends Player {

    public Goose(Node nodePosition) {
        super(nodePosition);
    }

    @Override
    public void move(String flag) {
        int xShift = getXShift(flag);
        int yShift = getYShift(flag);
    }
}
