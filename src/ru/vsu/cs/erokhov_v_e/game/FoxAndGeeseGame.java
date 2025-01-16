package ru.vsu.cs.erokhov_v_e.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoxAndGeeseGame {
    private Strategy foxStrategy;
    private Strategy geeseStrategy;
    private boolean foxTurn = true;
    private boolean gameIsOver = false;
    private GameField gameField = new GameField();
    private Fox fox = new Fox();
    private List<Goose> geese = new ArrayList<>();
    private String winner = "";
    private final Scanner SCANNER = new Scanner(System.in);
    private int geeseAmount;

    public FoxAndGeeseGame(Strategy foxStrategy, Strategy geeseStrategy, int geeseAmount) {
        this.foxStrategy = foxStrategy;
        this.geeseStrategy = geeseStrategy;
        this.geeseAmount = geeseAmount;
    }

    public void play() {
        placeGeese(geeseAmount);
        System.out.println("\nИгровое поле после расстановки гусей:");
        gameField.displayGameFieldMap();

        foxStrategy.placeFox(fox, gameField);

        System.out.println("\n|--Команды для хода:--|\n|U /u  - вверх        |\n|UR/ur - вверх, вправо|\n|R /r  - вправо       |\n|DR/dr - вниз, вправо |" +
                "\n|D /d  - вниз         |\n|DL/dl - вниз, влево  |\n|L /l  - влево        |\n|UL/ul - вверх, влево |");

        System.out.println("\nИгровое поле после расстановки лисы:");
        gameField.displayGameFieldMap();

        System.out.println("\nПервый ход делает лиса!");

        gameLoop:
        while (!gameIsOver) {
            if (foxTurn) {
                foxStrategy.moveFox(fox,geese,gameField);
                foxTurn = !foxTurn;
                System.out.println("\nИгровое поле после хода лисы:");
                gameField.displayGameFieldMap();
            }

            checkGameStatus();
            if (gameIsOver) {
                System.out.println("Игра завершена. Победитель" + winner + ".");
                break;
            }

            if (!foxTurn) {
                geeseStrategy.moveGoose(geese, gameField);
                foxTurn = !foxTurn;
                System.out.println("\nИгровое поле после хода гуся:");
                gameField.displayGameFieldMap();
            }

            checkGameStatus();
            if (gameIsOver) {
                System.out.println("Игра завершена. Победитель" + winner + ".");
                break;
            }
        }
    }

    public void checkGameStatus() {
        if (geese.size() <= 5) {
            gameIsOver = !gameIsOver;
            winner += " Лиса";
            return;
        }
        int x = fox.getNode().getCoordinate().getX();
        int y = fox.getNode().getCoordinate().getY();
        boolean geeseBlockedFox = true;
        List<Node> foxNodeConnections = fox.getNode().getConnections();
        upperLoop:
        for (Node node1 : foxNodeConnections) {
            if (node1.getStatus() != Status.GOOSE) {
                geeseBlockedFox = false;
                break upperLoop;
            }
            for (Node node2 : node1.getConnections()) {
                if ((Math.abs(node2.getCoordinate().getX()-x) == 0 && Math.abs(node2.getCoordinate().getY()-y) == 2) ||
                        (Math.abs(node2.getCoordinate().getX()-x) == 2 && Math.abs(node2.getCoordinate().getY()-y) == 0) ||
                        (Math.abs(node2.getCoordinate().getX()-x) == 2 && Math.abs(node2.getCoordinate().getY()-y) == 2)) {
                    if (node2.getStatus() != Status.GOOSE) {
                        geeseBlockedFox = false;
                        break upperLoop;
                    }
                }
            }
        }
        if (geeseBlockedFox) {
            gameIsOver = !gameIsOver;
            winner += " Гуси";
        }
    }

    public void placeGeese(int geeseAmount) {
        if (geeseAmount == 17) {
            for (int i = 4; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    // по ключу находим node и добавляем в goose этот node
                    Coordinate coordinate = new Coordinate(j,i);
                    if (gameField.getGameFieldMap().containsKey(coordinate)) {
                        Node node = gameField.getGameFieldMap().get(coordinate);
                        node.setStatus(Status.GOOSE);
                        Goose goose = new Goose(node);
                        geese.add(goose);
                    }
                }
            }
            for (int i = 0; i <= 6; i += 6) {
                for (int j = 2; j < 4; j++) {
                    Coordinate coordinate = new Coordinate(i,j);
                    if (gameField.getGameFieldMap().containsKey(coordinate)) {
                        Node node = gameField.getGameFieldMap().get(coordinate);
                        node.setStatus(Status.GOOSE);
                        Goose goose = new Goose(node);
                        geese.add(goose);
                    }
                }
            }
        } else {
            // по дефолту считается, что гусей 13
            for (int i = 4; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    // по ключу находим node и добавляем в goose этот node
                    Coordinate coordinate = new Coordinate(j,i);
                    if (gameField.getGameFieldMap().containsKey(coordinate)) {
                        Node node = gameField.getGameFieldMap().get(coordinate);
                        node.setStatus(Status.GOOSE);
                        Goose goose = new Goose(node);
                        geese.add(goose);
                    }
                }
            }
        }
    }
}
