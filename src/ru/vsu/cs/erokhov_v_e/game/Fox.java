package ru.vsu.cs.erokhov_v_e.game;

import java.awt.*;

public class Fox extends Player  {

    public Fox(Node nodePosition) {
        super(nodePosition);
    }

    public Fox() {

    }

//    @Override
//    public boolean move(String flag, GameField gameField) {
//        int xShift = getXShift(flag);
//        int yShift = getYShift(flag);
//        Node foxNode = this.getNode();
//        Point foxPoint = foxNode.getCoordinates();
//        if (!gameField.getGameFieldMap().containsKey((foxPoint.x + xShift)+","+(foxPoint.y + yShift))) {
//            return false;
//        }
//        Node goalNode = gameField.getGameFieldMap().get((foxPoint.x + xShift)+","+(foxPoint.y + yShift));
//        if (goalNode.getStatus() == null) {
//            foxNode.setStatus(null);
//            goalNode.setStatus(Status.FOX);
//            return true;
//        }
//        return false;
//    }
}
