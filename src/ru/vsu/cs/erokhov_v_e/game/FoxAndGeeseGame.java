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

    public static void checkGameStatus() {
        // изменить значение gameIsOver
    }

    public static void placeGeese(int geeseAmount) {
        if (geeseAmount == 17) {
            for (int i = 4; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    // по ключу находим node и добавляем в goose этот node
                    String key = j + "," + i;
                    if (gameField.getGameFieldMap().containsKey(key)) {
                        Node node = gameField.getGameFieldMap().get(key);
                        node.setStatus(Status.GOOSE);
                        Goose goose = new Goose(node);
                        geese.add(goose);
                    }
                }
            }
            for (int i = 0; i <= 6; i += 6) {
                for (int j = 2; j < 4; j++) {
                    String key = i + "," + j;
                    if (gameField.getGameFieldMap().containsKey(key)) {
                        Node node = gameField.getGameFieldMap().get(key);
                        node.setStatus(Status.GOOSE);
                        Goose goose = new Goose(node);
                        geese.add(goose);
                    }
                }
            }
        }
        else {
            // по дефолту считается, что гусей 13
            for (int i = 4; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    // по ключу находим node и добавляем в goose этот node
                    String key = j + "," + i;
                    if (gameField.getGameFieldMap().containsKey(key)) {
                        Node node = gameField.getGameFieldMap().get(key);
                        node.setStatus(Status.GOOSE);
                        Goose goose = new Goose(node);
                        geese.add(goose);
                    }
                }
            }
        }
    }

    public static void placeFox() {
        while (true) {
            System.out.print("\nВведите координаты для лисы. Координата x: ");
            int x = SCANNER.nextInt();
            System.out.print("Координата y: ");
            int y = SCANNER.nextInt();
            String key = x+","+y;
            if (gameField.getGameFieldMap().containsKey(key)) {
                Node node = gameField.getGameFieldMap().get(key);
                if (node.getStatus() == null) {
                    node = gameField.getGameFieldMap().get(key);
                    node.setStatus(Status.FOX);
                    fox.setNode(node);
                    break;
                } else {
                    System.out.println("На этих координатах расположен гусь.");
                }
            } else {
                System.out.println("Значения координат находятся за пределами игрового поля.");
            }
        }
    }

    public static void main(String[] args) {
        gameField.createGameFieldMap();

        System.out.print("Укажите сколько гусей будет в игре: 13 или 17? - ");
        int geeseAmount = SCANNER.nextInt();
        placeGeese(geeseAmount);
        System.out.println("\nИгровое поле после расстановки гусей:");
        gameField.displayGameFieldMap();

        placeFox();

        System.out.println("\nИгровое поле после расстановки лисы:");
        gameField.displayGameFieldMap();

        System.out.println("\nПервый ход делает лиса!");

//        gameLoop:
//        while (!gameIsOver) {
//            while (foxTurn) {
//                System.out.println("");
//            }
//            if (!foxTurn) {
//                System.out.println();
//                foxTurn = !foxTurn;
//            }
//
//            System.out.println("\nИгровое поле после хода: ");
//            gameField.displayGameFieldMap();
//
//            if (gameIsOver) {
//                break gameLoop;
//            }
//
//        }

        // первый ход делает лиса, ее выбирать не нужно, после хода лисы нужно выбрать за какого гуся ходить, спрашиваем у пользователя координаты и проверяем их правильность,
        // добавить возможность изменить выбор, потом спросить куда нужно походить, опять проверяем их правильность (создать метод отдельный), потом проверяем возможность хода
    }
}
