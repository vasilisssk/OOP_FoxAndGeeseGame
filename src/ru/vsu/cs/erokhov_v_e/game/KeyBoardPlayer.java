package ru.vsu.cs.erokhov_v_e.game;

import java.util.List;

import static ru.vsu.cs.erokhov_v_e.game.Util.*;


public class KeyBoardPlayer implements Strategy {

    @Override
    public void placeFox(Fox fox, GetNode getNodeInter) {
        System.out.print("\nРазместите лису.");
        while (true) {
            Coordinate coordinate = writeUserCoordinate();
            Node node = getNodeInter.getNode(coordinate);
            if (node == null) {
                System.out.println("Значения координат находятся за пределами игрового поля. Введите их заново.");
            }
            else if (node.getStatus() == Status.GOOSE) {
                System.out.println("На этих координатах расположен гусь. Введите координаты заново.");
            }
            else if (node.getStatus() == Status.EMPTY) {
                node.setStatus(Status.FOX);
                fox.setNode(node);
                break;
            }
        }
    }

    @Override
    public boolean moveFox(Fox fox, List<Goose> geese, CalculateMoves calculateMoves) {
        System.out.print("Введите ход для лисы: ");
        Movement movement = writeUserMovementFox();
        while (true) {
            Boolean gooseWasEaten = calculateMoves.calculateFoxMove(movement, fox, geese);
            if (gooseWasEaten == null) {
                return true;
            }
            else if (!gooseWasEaten) {
                System.out.print("Неверные данные для хода. Введите код команды повторно: ");
                movement = writeUserMovementFox();
            } else {
                return false;
            }

        }
    }

    @Override
    public void moveGoose(List<Goose> geese, CalculateMoves calculateMoves) {
        System.out.print("\nВыберите гуся за которого хотите походить.");
        Coordinate coordinate = writeUserCoordinate();

        Goose goose = null;
        upperLoop:
        while (true) {
            int counter = 0;
            for (int i = 0; i < geese.size(); i++) {
                goose = geese.get(i);
                if (!goose.getNode().getCoordinate().equals(coordinate)) {
                    counter++;
                } else {
                    break upperLoop;
                }
            }
            if (counter == geese.size()) {
                System.out.println("На этих координатах нет гуся. Введите их заново.");
                coordinate = writeUserCoordinate();
            }
            else {
                break;
            }
        }

        System.out.print("Введите ход для гуся: ");
        Movement movement = writeUserMovementGoose();

        while (!calculateMoves.calculateGooseMove(movement, goose)) {
            System.out.print("Неверные данные для хода. Введите код команды повторно: ");
            movement = writeUserMovementGoose();
        }
    }
}
