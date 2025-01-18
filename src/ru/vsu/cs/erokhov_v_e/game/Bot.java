package ru.vsu.cs.erokhov_v_e.game;

import java.util.Arrays;
import java.util.List;

import static ru.vsu.cs.erokhov_v_e.game.Util.*;

public class Bot implements Strategy {

    public static final Factory FACTORY = new Factory() {
        @Override
        public Strategy create() {
            return new Bot();
        }
    };

   @Override
    public void placeFox(Fox fox, GetNode getNodeInter) {
       System.out.println(Arrays.toString(Movement.values()));
       System.out.println("Бот расставляет лису.");
       Coordinate coordinate;
       while (true) {
           coordinate = writeBotCoordinate();
           Node node = getNodeInter.getNode(coordinate);
           if (node.getStatus() == NodeStatus.EMPTY) {
               node.setStatus(NodeStatus.FOX);
               fox.setNode(node);
               break;
           }
       }
       System.out.printf("\nБот поставил лису на координаты [x=%1s, y=%2s]", coordinate.getX(), coordinate.getY());
    }

    @Override
    public boolean moveFox(Fox fox, List<Goose> geese, CalculateMoves calculateMoves) {
       //calculateMoves.calculateGooseMove()
        System.out.println("Бот перемещает лису.");
        Movement movement = pickBotRandomMovementFox();
        while (true) {
            Boolean gooseWasEaten = calculateMoves.calculateFoxMove(movement, fox, geese);
            if (gooseWasEaten == null) {
                System.out.printf("\nБот переместил лису командой %1s", movement);
                return true;
            }
            else if (!gooseWasEaten) {
                movement = pickBotRandomMovementFox();
            } else {
                System.out.printf("\nБот переместил лису командой %1s", movement);
                return false;
            }
        }
    }

    @Override
    public void moveGoose(List<Goose> geese, CalculateMoves calculateMoves) {
       //geese.get(0).setNode(new Node(new Coordinate(100, 100), Status.GOOSE));
       //geese.get(0).getNode().getConnections()

        System.out.println("Бот перемещает гуся.");
        Goose goose = null;
        upperLoop1:
        for (int i = 0; i < geese.size(); i++) {
            goose = geese.get(i);
            int gooseX = goose.getNode().getCoordinate().getX();
            int gooseY = goose.getNode().getCoordinate().getY();
            List<Node> connectedNodes = goose.getNode().getConnections();
            for (int j = 0; j < connectedNodes.size(); j++) {
                Node connectedNode = connectedNodes.get(j);
                int connectedNodeX = connectedNode.getCoordinate().getX();
                int connectedNodeY = connectedNode.getCoordinate().getY();
                if (Math.abs(gooseX-connectedNodeX) != 0 && Math.abs(gooseY-connectedNodeY) != 0) {
                    continue;
                }
                if (connectedNodes.get(j).getStatus() == NodeStatus.EMPTY) {
                    break upperLoop1;
                }
            }
        }
        int x = goose.getNode().getCoordinate().getX();
        int y = goose.getNode().getCoordinate().getY();
        Movement movement = pickBotRandomMovementGoose();

        while (!calculateMoves.calculateGooseMove(movement, goose)) {
            movement = pickBotRandomMovementGoose();
        }

        System.out.printf("\nБот переместил гуся по координатам [x=%1s, y=%2s] командой %3s", x, y, movement);
   }
}
