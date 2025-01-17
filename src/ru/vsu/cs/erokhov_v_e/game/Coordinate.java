package ru.vsu.cs.erokhov_v_e.game;

import java.util.Objects;

public class Coordinate {

    private final int x;
    private final int  y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate that)) return false;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate get(Movement m) {
        return get(m, 1);
    }
    public Coordinate get(Movement m, int mult) {
        return new Coordinate(x + mult*m.getDx(), y + mult*m.getDy());
    }
}
