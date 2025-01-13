package ru.vsu.cs.erokhov_v_e.game;

import java.awt.*;

public class Goose extends Player {

    public Goose(Node nodePosition) {
        super(nodePosition);
    }

//    @Override
//    public boolean move(String flag, GameField gameField) {
//        int xShift = getXShift(flag);
//        int yShift = getYShift(flag);
//        Node gooseNode = this.getNode();
//        Point goosePoint = gooseNode.getCoordinates();
//        if (!gameField.getGameFieldMap().containsKey((goosePoint.x + xShift)+","+(goosePoint.y + yShift))) {
//            return false;
//        }
//        Node goalNode = gameField.getGameFieldMap().get((goosePoint.x + xShift)+","+(goosePoint.y + yShift));
//        if (goalNode.getStatus() == null) {
//            gooseNode.setStatus(null);
//            goalNode.setStatus(Status.GOOSE);
//            return true;
//        }
//        return false;
//    }
}
