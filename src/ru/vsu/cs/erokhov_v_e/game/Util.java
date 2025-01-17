package ru.vsu.cs.erokhov_v_e.game;

import java.util.Random;
import java.util.Scanner;

public class Util {

    private final static Scanner scanner = new Scanner(System.in);
    private final static Random random = new Random();

    public static int checkInt() {
        int number;
        while (true) {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                break;
            } else {
                System.out.print("Введено неверное значение int. Введите его повторно: ");
                scanner.next();
            }
        }
        return number;
    }

    public static Coordinate writeUserCoordinate() {
        System.out.print("\nКоордината x: ");
        int x = checkInt();
        System.out.print("Координата y: ");
        int y = checkInt();
        return new Coordinate(x, y);
    }

    public static Movement writeUserMovementFox() {
        String flag = scanner.next().toUpperCase();
        Movement movement = Movement.fromString(flag);

        while (movement == null) {
            System.out.print("Неверное значение хода. Введите его заново:  ");
            flag = scanner.next().toUpperCase();
            movement = Movement.fromString(flag);
        }
        return movement;
    }

    public static Movement writeUserMovementGoose() {
        String flag = scanner.next().toUpperCase();
        Movement movement = Movement.fromString(flag);

        while (movement == null || movement == Movement.UP_RIGHT || movement == Movement.DOWN_RIGHT
                || movement == Movement.DOWN_LEFT || movement == Movement.UP_LEFT) {
            System.out.print("Неверное значение хода. Введите его заново:  ");
            flag = scanner.next().toUpperCase();
            movement = Movement.fromString(flag);
        }
        return movement;
    }

    public static Movement calculateMovement(Coordinate coordinate1, Coordinate coordinate2) {
        int dx = coordinate2.getX() - coordinate1.getX();
        int dy = coordinate2.getY() - coordinate1.getY();
        return Movement.getMovement(dx,dy);
    }

    public static Coordinate writeBotCoordinate() {
        int x = random.nextInt(0,6);
        int y = random.nextInt(0,6);
        while (((x <= 1 && y <= 1) || (x <= 1 && y >= 5) || (x >= 5 && y <= 1) || (x >= 5 && y >= 5))) {
            x = random.nextInt(0,6);
            y = random.nextInt(0,6);
        }
        return new Coordinate(x,y);
    }

    public static Movement pickBotRandomMovementFox() {
        Movement[] movementArr = Movement.values();
        int rnd = random.nextInt(0, movementArr.length);
        return movementArr[rnd];
    }

    public static Movement pickBotRandomMovementGoose() {
        Movement[] movementArr = Movement.values();
        int rnd = random.nextInt(0, movementArr.length-4);
        return movementArr[rnd];
    }
}
