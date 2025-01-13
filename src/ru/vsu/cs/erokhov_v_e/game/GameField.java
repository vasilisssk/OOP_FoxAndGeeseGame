package ru.vsu.cs.erokhov_v_e.game;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameField {
    private Map<String, Node> gameFieldMap = new HashMap<>();

    public GameField() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (!((i <= 1 && j <= 1) || (i <= 1 && j >= 5) || (i >= 5 && j <= 1) || (i >= 5 && j >= 5))) {
                    String key = i + "," + j;
                    gameFieldMap.put(key, new Node(new Point(i, j), null/*Status.EMPTY*/));
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (!((i <= 1 && j <= 1) || (i <= 1 && j >= 5) || (i >= 5 && j <= 1) || (i >= 5 && j >= 5))) {
                    String key = i + "," + j;
                    Node node = gameFieldMap.get(key);
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (!(k == 0 && l == 0)) {
                                String key2 = (i+l) + "," + (j+k);
                                if (gameFieldMap.containsKey(key2)) {
                                    node.connect(gameFieldMap.get(key2));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

//    public void createGameFieldMap() {
//
//    }

    public void displayGameFieldMap() {
        System.out.println("  0123456");
        for (int i = 0; i < 7; i++) {
            String finalString = i + " ";
            for (int j = 0; j < 7; j++) {
                String key = j + "," + i;
                if (gameFieldMap.containsKey(key)) {
                    Status status = gameFieldMap.get(key).getStatus();
                    if (status == Status.FOX) {
                        finalString+="F";
                    } else if (status == Status.GOOSE) {
                        finalString+="G";
                    } else {
                        finalString+="x";
                    }
                } else {
                    finalString+="-";
                }
            }
            System.out.println(finalString);
        }
    }

    public Map<String, Node> getGameFieldMap() {
        return new HashMap<>(gameFieldMap);
    }

    public void setGameFieldMap(Map<String, Node> gameFieldMap) {
        this.gameFieldMap = new HashMap<>(gameFieldMap);
    }
}
