package ru.vsu.cs.erokhov_v_e.game;

import java.util.HashMap;
import java.util.Map;

public class GameField {
    private Map<Coordinate, Node> gameFieldMap = new HashMap<>();

    public GameField() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (!((i <= 1 && j <= 1) || (i <= 1 && j >= 5) || (i >= 5 && j <= 1) || (i >= 5 && j >= 5))) {
                    Coordinate coordinate = new Coordinate(i,j);
                    gameFieldMap.put(coordinate, new Node(new Coordinate(i, j), Status.EMPTY));
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
                Coordinate coordinates = new Coordinate(j,i);
                if (gameFieldMap.containsKey(coordinates)) {
                    Status status = gameFieldMap.get(coordinates).getStatus();
                    if (status == Status.FOX) {
                        finalString+="F";
                    } else if (status == Status.GOOSE) {
                        finalString+="G";
                    } else if (status == Status.EMPTY) {
                        finalString+="x";
                    }
                } else {
                    finalString+="-";
                }
            }
            System.out.println(finalString);
        }
    }

    public Map<Coordinate, Node> getGameFieldMap() {
        return Map.copyOf(gameFieldMap);
    }

    public void setGameFieldMap(Map<Coordinate, Node> gameFieldMap) {
        this.gameFieldMap = Map.copyOf(gameFieldMap);
    }
}
