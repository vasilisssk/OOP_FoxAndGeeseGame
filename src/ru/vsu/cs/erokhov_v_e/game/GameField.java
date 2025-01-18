package ru.vsu.cs.erokhov_v_e.game;

import java.util.HashMap;
import java.util.Map;

public class GameField implements GetNode {
    private Map<Coordinate, Node> gameFieldMap = new HashMap<>();

    public GameField() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (!((i <= 1 && j <= 1) || (i <= 1 && j >= 5) || (i >= 5 && j <= 1) || (i >= 5 && j >= 5))) {
                    Coordinate coordinate = new Coordinate(i,j);
                    gameFieldMap.put(coordinate, new Node(new Coordinate(i, j), NodeStatus.EMPTY));
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (!((i <= 1 && j <= 1) || (i <= 1 && j >= 5) || (i >= 5 && j <= 1) || (i >= 5 && j >= 5))) {
                    Coordinate coordinate = new Coordinate(i,j);
                    Node node = gameFieldMap.get(coordinate);
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (!(k == 0 && l == 0)) {
                                Coordinate coordinates2 = new Coordinate(i+l, j+k);
                                if (gameFieldMap.containsKey(coordinates2)) {
                                    node.connect(gameFieldMap.get(coordinates2));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void displayGameFieldMap() {
        System.out.println("  0123456");
        for (int i = 0; i < 7; i++) {
            String finalString = i + " ";
            for (int j = 0; j < 7; j++) {
                Coordinate coordinates = new Coordinate(j, i);
                if (gameFieldMap.containsKey(coordinates)) {
                    NodeStatus status = gameFieldMap.get(coordinates).getStatus();
                    if (status == NodeStatus.FOX) {
                        finalString += "F";
                    } else if (status == NodeStatus.GOOSE) {
                        finalString += "G";
                    } else if (status == NodeStatus.EMPTY) {
                        finalString += "x";
                    }
                } else {
                    finalString += "-";
                }
            }
            System.out.println(finalString);
        }
    }

    public Map<Coordinate, Node> getGameFieldMap() {
        return Map.copyOf(gameFieldMap);
    }

    @Override
    public Node getNode(Coordinate coordinate) {
        return gameFieldMap.getOrDefault(coordinate, null);
    }
}
