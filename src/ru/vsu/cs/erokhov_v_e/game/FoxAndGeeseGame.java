package ru.vsu.cs.erokhov_v_e.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoxAndGeeseGame {
    private static boolean foxTurn = true;
    private static boolean gameIsOver = false;
    private static GameField gameField = new GameField();
    private static Fox fox = new Fox();
    private static List<Goose> geese = new ArrayList<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    public void checkGameStatus() {
        // изменить значение gameIsOver
    }

    public static void main(String[] args) {
        // можно отслеживать флаг --seventeen и вместо 13 гусей добавлять 17, через switch-case
        gameField.createGameFieldMap();
        for (int j = 4; j < 7; j++) {
            for (int k = 0; k < 7; k++) {
                String key = k + "," + j;
                if (gameField.getGameFieldMap().containsKey(key)) {
                    Node node = gameField.getGameFieldMap().get(key);
                    node.setStatus(Status.GOOSE);
                    Goose goose = new Goose(node);
                    geese.add(goose);
                }
                // по ключу находим node и добавляем в goose этот node
            }
        }

        System.out.print("Введите координаты для лисы. Координата x: ");
        int x = SCANNER.nextInt();
        System.out.print("Координата y: ");
        int y = SCANNER.nextInt();
        while (y >= 4 || (x <= 1 && y <= 1 || x >= 5 && y <= 1)) {
            System.out.print("Неправильные значения координат! Введите их повторно. Новая координата x: ");
            x = SCANNER.nextInt();
            System.out.print("Новая координата y: ");
            y = SCANNER.nextInt();
        }

        String key = x + "," + y;
        Node node = gameField.getGameFieldMap().get(key);
        node.setStatus(Status.FOX);
        fox.setNode(node);

        gameField.displayGameFieldMap();


//        while (!gameIsOver) {
//            break;
//        }

        // первый ход делает лиса, ее выбирать не нужно, после хода лисы нужно выбрать за какого гуся ходить, спрашиваем у пользователя координаты и проверяем их правильность,
        // добавить возможность изменить выбор, потом спросить куда нужно походить, опять проверяем их правильность (создать метод отдельный), потом проверяем возможность хода
    }
}
