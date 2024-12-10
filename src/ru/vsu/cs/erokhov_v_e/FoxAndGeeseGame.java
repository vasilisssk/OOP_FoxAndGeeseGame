package ru.vsu.cs.erokhov_v_e;

public class FoxAndGeeseGame {
    private int turnToMove = 0;

    public static void main(String[] args) {
        GameField gameField = new GameField();
        gameField.createGameField();
    }
}
