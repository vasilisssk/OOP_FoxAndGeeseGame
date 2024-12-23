package ru.vsu.cs.erokhov_v_e;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameField {
    Map<String, Node> gameField = new HashMap<>();

    public void createGameField() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (!((i <= 1 && j <= 1) || (i <= 1 && j >= 5) || (i >= 5 && j <= 1) || (i >= 5 && j >= 5))) {
                    String key = i + "," + j;
                    gameField.put(key, new Node(new Point(i, j), null/*Status.EMPTY*/));
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (!((i <= 1 && j <= 1) || (i <= 1 && j >= 5) || (i >= 5 && j <= 1) || (i >= 5 && j >= 5))) {
                    String key = i + "," + j;
                    Node node = gameField.get(key);
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (!(k == 0 && l == 0)) {
                                String key2 = (j+l) + "," + (i+k);
                                if (gameField.containsKey(key2)) {
                                    node.connect(gameField.get(key2));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Map<String, Node> getGameField() {
        return gameField;
    }

    public void setGameField(Map<String, Node> gameField) {
        this.gameField = gameField;
    }
}
