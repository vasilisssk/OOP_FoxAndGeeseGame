package ru.vsu.cs.erokhov_v_e.game;

public class Fox extends Player  {

    public Fox(Node nodePosition) {
        super(nodePosition);
    }

    public Fox() {

    }

    @Override
    public void move(String flag) {
        int xShift = getXShift(flag);
        int yShift = getYShift(flag);
    }
}
