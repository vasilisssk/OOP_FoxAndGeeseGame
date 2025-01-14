package ru.vsu.cs.erokhov_v_e.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoxAndGeeseGame {
    private static boolean foxTurn = true;
    private static boolean gameIsOver = false;
    private static GameField gameField = new GameField();
    private static Fox fox = new Fox();
    private static List<Goose> geese = new ArrayList<>();
    private static String winner = "";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String[] commands = new String[] {"U", "R", "D", "L", "UR", "DR", "DL", "UL"};

    public static void checkGameStatus() {
        if (geese.size() <= 5) {
            gameIsOver = !gameIsOver;
            winner += " Лиса";
            return;
        }
        int x = fox.getNode().getCoordinates().x;
        int y = fox.getNode().getCoordinates().y;
        boolean geeseBlockedFox = true;
        List<Node> foxNodeConnections = fox.getNode().getConnections();
        upperLoop:
        for (Node node1 : foxNodeConnections) {
            if (node1.getStatus() != Status.GOOSE) {
                geeseBlockedFox = false;
                break upperLoop;
            }
            for (Node node2 : node1.getConnections()) {
                if ((Math.abs(node2.getCoordinates().x-x) == 0 && Math.abs(node2.getCoordinates().y-y) == 2) ||
                        (Math.abs(node2.getCoordinates().x-x) == 2 && Math.abs(node2.getCoordinates().y-y) == 0) ||
                        (Math.abs(node2.getCoordinates().x-x) == 2 && Math.abs(node2.getCoordinates().y-y) == 2)) {
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
        } else {
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
            int x = checkInt();
            System.out.print("Координата y: ");
            int y = checkInt();
            String key = x + "," + y;
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

    public static boolean moveFox(String flag, Fox fox) {
        int counter = 0;
        for (String command : commands) {
            if (!flag.equals(command)) {
                counter++;
            }
        }
        if (counter == commands.length) {
            return false;
        }
        int xShift = fox.getXShift(flag);
        int yShift = fox.getYShift(flag);
        Node foxNode = fox.getNode();
        Point foxPoint = foxNode.getCoordinates();
        Point goalPoint = new Point(foxPoint.x + xShift, foxPoint.y + yShift);
        if (!gameField.getGameFieldMap().containsKey(goalPoint.x + "," + goalPoint.y)) {
            return false;
        }
        Node connectedNode = null;
        for (int i = 0; i < foxNode.getConnections().size(); i++) {
            connectedNode = foxNode.getConnections().get(i);
            if (connectedNode.getCoordinates().equals(goalPoint)) {
                break;
            }
        }
        if (connectedNode.getStatus() == null) {
            foxNode.setStatus(null);
            fox.setNode(connectedNode);
            connectedNode.setStatus(Status.FOX);
            return true;
        }
        if (connectedNode.getStatus() == Status.GOOSE) {
            Point eatGoosePoint = new Point(foxPoint.x + (2 * xShift), foxPoint.y + (2 * yShift));
            Node eatGooseNode;
            for (int i = 0; i < connectedNode.getConnections().size(); i++) {
                eatGooseNode = connectedNode.getConnections().get(i);
                if (eatGooseNode.getCoordinates().equals(eatGoosePoint)) {
                    if (eatGooseNode.getStatus() == Status.GOOSE) {
                        System.out.println("Можно съесть гуся, если только по итогу хода вы окажитесь на свободной клетке!");
                        return false;
                    }
                    foxNode.setStatus(null);
                    fox.setNode(eatGooseNode);
                    eatGooseNode.setStatus(Status.FOX);
                    eatGoose(connectedNode);
                    System.out.println("Лиса съела гуся по координатам: x=" + goalPoint.x + ", y=" + goalPoint.y + ". Гусей осталось: " + geese.size() + ". Ходит снова лиса.");
                    foxTurn = !foxTurn;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean moveGoose(String flag, Goose goose) {
        int counter = 0;
        for  (int i = 0; i < commands.length - 4; i++) {
            if (!flag.equals(commands[i])) {
                counter++;
            }
        }
        if (counter == commands.length-4) {
            return false;
        }
        int xShift = goose.getXShift(flag);
        int yShift = goose.getYShift(flag);
        Node gooseNode = goose.getNode();
        Point foxPoint = gooseNode.getCoordinates();
        Point goalPoint = new Point(foxPoint.x + xShift, foxPoint.y + yShift);
        if (!gameField.getGameFieldMap().containsKey(goalPoint.x + "," + goalPoint.y)) {
            return false;
        }
        Node connectedNode = null;
        for (int i = 0; i < gooseNode.getConnections().size(); i++) {
            connectedNode = gooseNode.getConnections().get(i);
            if (connectedNode.getCoordinates().equals(goalPoint)) {
                break;
            }
        }
        if (connectedNode.getStatus() == null) {
            gooseNode.setStatus(null);
            goose.setNode(connectedNode);
            connectedNode.setStatus(Status.GOOSE);
            return true;
        }
        return false;
    }

    public static void eatGoose(Node node) {
        for (int i = 0; i < geese.size(); i++) {
            if (geese.get(i).getNode().equals(node)) {
                geese.remove(i);
                break;
            }
        }
        node.setStatus(null);
    }

    public static int checkInt() {
        int number;
        while (true) {
            if (SCANNER.hasNextInt()) {
                number = SCANNER.nextInt();
                break;
            } else {
                System.out.print("Введено неверное значение int. Введите его повторно: ");
                SCANNER.next();
            }
        }
        return number;
    }

    public static void main(String[] args) {
        System.out.print("Укажите сколько гусей будет в игре: 13 или 17 (обычно всегда 13 гусей)? - ");
        int geeseAmount = checkInt();
        placeGeese(geeseAmount);
        System.out.println("\nИгровое поле после расстановки гусей:");
        gameField.displayGameFieldMap();

        placeFox();

        System.out.println("\nИгровое поле после расстановки лисы:");
        gameField.displayGameFieldMap();

        System.out.println("\n|--Команды для хода:--|\n|U /u  - вверх        |\n|UR/ur - вверх, вправо|\n|R /r  - вправо       |\n|DR/dr - вниз, вправо |" +
                "\n|D /d  - вниз         |\n|DL/dl - вниз, влево  |\n|L /l  - влево        |\n|UL/ul - вверх, влево |");
        System.out.println("\nПервый ход делает лиса!");

        gameLoop:
        while (!gameIsOver) {
            if (foxTurn) {
                System.out.print("Введите ход для лисы: ");
                String flag = SCANNER.next().toUpperCase();
                while (!moveFox(flag, fox)) {
                    System.out.print("Неверные данные для хода. Введите код команды повторно: ");
                    flag = SCANNER.next().toUpperCase();
                }
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
                System.out.print("Выберите гуся за которого хотите походить. Координата x: ");
                int x = checkInt();
                System.out.print("Координата y: ");
                int y = checkInt();
                String key = x + "," + y;
                while (true) {
                    while (!gameField.getGameFieldMap().containsKey(key)) {
                        System.out.print("Неверные координаты. Введите их заново. Координата x: ");
                        x = checkInt();
                        System.out.print("Координата y: ");
                        y = checkInt();
                        key = x + "," + y;
                    }
                    Node node = gameField.getGameFieldMap().get(key);
                    while (node.getStatus() != Status.GOOSE) {
                        System.out.print("На этих координатах нет гуся. Введите их заново. Координата x: ");
                        x = checkInt();
                        System.out.print("Координата y: ");
                        y = checkInt();
                        key = x + "," + y;
                        node = gameField.getGameFieldMap().get(key);
                    }
                    break;
                }
                Goose goose = null;
                for (int i = 0; i < geeseAmount; i++) {
                    goose = geese.get(i);
                    if (goose.getNode().equals(gameField.getGameFieldMap().get(key))) {
                        break;
                    }
                }
                System.out.print("Введите ход для гуся: ");
                String flag = SCANNER.next().toUpperCase();
                while (!moveGoose(flag, goose)) {
                    System.out.print("Неверные данные для хода. Введите код команды повторно: ");
                    flag = SCANNER.next().toUpperCase();
                }
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
}
