package ru.vsu.cs.erokhov_v_e.game;

import java.util.Map;
import java.util.TreeMap;

public enum Movement {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0,1),
    LEFT(-1,0),
    UP_RIGHT(1,-1),
    DOWN_RIGHT(1,1),
    DOWN_LEFT(-1,1),
    UP_LEFT(-1,-1);


    private final int dy;
    private final int dx;

    Movement(int dx, int dy) {
        this.dy = dy;
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    private static Map<String, Movement> byString = new TreeMap<>();
    static {
        byString.put("U", UP);
        byString.put("R", RIGHT);
        byString.put("D", DOWN);
        byString.put("L", LEFT);
        byString.put("UR", UP_RIGHT);
        byString.put("DR", DOWN_RIGHT);
        byString.put("DL", DOWN_LEFT);
        byString.put("UL", UP_LEFT);
    }

    public static Movement fromString(String s) {
        return byString.getOrDefault(s, null);
    }
}
