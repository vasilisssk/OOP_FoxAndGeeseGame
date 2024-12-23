package ru.vsu.cs.erokhov_v_e;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoxAndGeeseGame {
    private static int turnToMove = 0;
    private static boolean gameIsOver = false;
    private static GameField gameField = new GameField();
    private static Fox fox;
    private static List<Goose> geese = new ArrayList<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        // можно отслеживать флаг --seventeen и вместо 13 гусей добавлять 17, через switch-case
        gameField.createGameField();
        for (int j = 4; j < 7; j++) {
            for (int k = 0; k < 7; k++) {
                String key = k + "," + j;
                if (gameField.getGameField().containsKey(key)) {
                    Node node = gameField.getGameField().get(key);
                    Goose goose = new Goose(node);
                    geese.add(goose);
                }
                // по ключу находим Node и добавляем в goose этот node
            }
        }
        for (int i = 0; i < geese.size(); i++) {
            Goose goose = geese.get(i);
            System.out.println(goose.getNode().getCoordinates().toString());
        }
//        System.out.print("Введите координаты для лисы. Координата x: ");
//        int x = SCANNER.nextInt();
//        System.out.print("Координата y: ");
//        int y = SCANNER.nextInt();
//        while (y >= 4 || (x <= 1 && y <= 1 || x >= 5 && y <= 1)) {
//            System.out.print("Неправильные значения координат! Введите их повторно. Новая координата x: ");
//            x = SCANNER.nextInt();
//            System.out.print("Новая координата y: ");
//            y = SCANNER.nextInt();
//        }
    }
}
