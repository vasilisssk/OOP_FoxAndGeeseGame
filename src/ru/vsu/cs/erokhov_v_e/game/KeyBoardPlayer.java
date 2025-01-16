package ru.vsu.cs.erokhov_v_e.game;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Scanner;

import static ru.vsu.cs.erokhov_v_e.game.Util.checkInt;

public class KeyBoardPlayer implements Strategy {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void placeFox(Fox fox, GameField gameField) {
        while (true) {
            System.out.print("\nВведите координаты для лисы. Координата x: ");
            int x = checkInt();
            System.out.print("Координата y: ");
            int y = checkInt();
            Coordinate coordinate = new Coordinate(x, y);
            if (gameField.getGameFieldMap().containsKey(coordinate)) {
                Node node = gameField.getGameFieldMap().get(coordinate);
                if (node.getStatus() == Status.EMPTY) {
                    node = gameField.getGameFieldMap().get(coordinate);
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

    @Override
    public void moveFox(Fox fox, List<Goose> geese, GameField gameField) {
        System.out.print("Введите ход для лисы: ");
        String flag = scanner.next().toUpperCase();
        Movement movement = Movement.fromString(flag);
        while (!calculateFoxMove(movement, fox, gameField, geese)) {
            System.out.print("Неверные данные для хода. Введите код команды повторно: ");
            flag = scanner.next().toUpperCase();
            movement = Movement.fromString(flag);
        }
    }

    @Override
    public void moveGoose(List<Goose> geese, GameField gameField) {
        System.out.print("Выберите гуся за которого хотите походить. Координата x: ");
        int x = checkInt();
        System.out.print("Координата y: ");
        int y = checkInt();
        Coordinate coordinate = new Coordinate(x, y);

        while (true) {
            while (!gameField.getGameFieldMap().containsKey(coordinate)) {
                System.out.print("Неверные координаты. Введите их заново. Координата x: ");
                x = checkInt();
                System.out.print("Координата y: ");
                y = checkInt();
                coordinate = new Coordinate(x, y);
            }
            Node node = gameField.getGameFieldMap().get(coordinate);

            while (node.getStatus() != Status.GOOSE) {
                System.out.print("На этих координатах нет гуся. Введите их заново. Координата x: ");
                x = checkInt();
                System.out.print("Координата y: ");
                y = checkInt();
                coordinate = new Coordinate(x, y);
                node = gameField.getGameFieldMap().get(coordinate);
            }
            break;
        }

        Goose goose = null;
        for (int i = 0; i < geese.size(); i++) {
            goose = geese.get(i);
            if (goose.getNode().equals(gameField.getGameFieldMap().get(coordinate))) {
                break;
            }
        }

        System.out.print("Введите ход для гуся: ");
        String flag = scanner.next().toUpperCase();
        Movement movement = Movement.fromString(flag);

        while (movement == null) {
            System.out.print("Неверное значение хода. Введите его заново:  ");
            flag = scanner.next().toUpperCase();
            movement = Movement.fromString(flag);
        }


        while (!calculateGooseMove(movement, goose, gameField)) {
            System.out.print("Неверные данные для хода. Введите код команды повторно: ");
            flag = scanner.next().toUpperCase();
            movement = Movement.fromString(flag);
        }
    }

    public boolean calculateGooseMove(Movement movement, Goose goose, GameField gameField) {
        if (movement == null) {
            return false;
        }
        Node gooseNode = goose.getNode();
        Coordinate gooseCoordinate = gooseNode.getCoordinate();
        Coordinate goalCoordinate = gooseCoordinate.get(movement);
        if (!gameField.getGameFieldMap().containsKey(goalCoordinate)) {
            return false;
        }
        Node connectedNode = null;
        for (int i = 0; i < gooseNode.getConnections().size(); i++) {
            connectedNode = gooseNode.getConnections().get(i);
            if (connectedNode.getCoordinate().equals(goalCoordinate)) {
                break;
            }
        }
        if (connectedNode.getStatus() == Status.EMPTY) {
            gooseNode.setStatus(Status.EMPTY);
            goose.setNode(connectedNode);
            connectedNode.setStatus(Status.GOOSE);
            return true;
        }
        return false;
    }

    public boolean calculateFoxMove(Movement movement, Fox fox, GameField gameField, List<Goose> geese) {
        if (movement == null) {
            return false;
        }
        Node foxNode = fox.getNode();
        Coordinate foxCoordinate = foxNode.getCoordinate();
        Coordinate goalCoordinate = fox.getNode().getCoordinate().get(movement);
        if (!gameField.getGameFieldMap().containsKey(goalCoordinate)) {
            return false;
        }
        Node connectedNode = null;
        for (int i = 0; i < foxNode.getConnections().size(); i++) {
            connectedNode = foxNode.getConnections().get(i);
            if (connectedNode.getCoordinate().equals(goalCoordinate)) {
                break;
            }
        }
        if (connectedNode.getStatus() == Status.EMPTY) {
            foxNode.setStatus(Status.EMPTY);
            fox.setNode(connectedNode);
            connectedNode.setStatus(Status.FOX);
            return true;
        }
        if (connectedNode.getStatus() == Status.GOOSE) {
            Coordinate eatGooseCoordinate = foxCoordinate.get(movement, 2);
            Node eatGooseNode;
            for (int i = 0; i < connectedNode.getConnections().size(); i++) {
                eatGooseNode = connectedNode.getConnections().get(i);
                if (eatGooseNode.getCoordinate().equals(eatGooseCoordinate)) {
                    if (eatGooseNode.getStatus() == Status.GOOSE) {
                        System.out.println("Можно съесть гуся, если только по итогу хода вы окажитесь на свободной клетке!");
                        return false;
                    }
                    foxNode.setStatus(Status.EMPTY);
                    fox.setNode(eatGooseNode);
                    eatGooseNode.setStatus(Status.FOX);
                    eatGoose(connectedNode, geese);
                    System.out.println("Лиса съела гуся по координатам: x=" + goalCoordinate.getX() + ", y=" + goalCoordinate.getY() + ". Гусей осталось: " + geese.size() + ". Ходит снова лиса.");
                    moveFox(fox, geese, gameField);
                }
            }
        }
        return false;
    }

    public void eatGoose(Node node, List<Goose> geese) {
        for (int i = 0; i < geese.size(); i++) {
            if (geese.get(i).getNode().equals(node)) {
                geese.remove(i);
                break;
            }
        }
        node.setStatus(Status.EMPTY);
    }
}
