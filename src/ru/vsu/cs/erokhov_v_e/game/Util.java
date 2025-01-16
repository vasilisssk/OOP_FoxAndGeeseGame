package ru.vsu.cs.erokhov_v_e.game;

import java.util.Scanner;

public class Util {

    private final static Scanner scanner = new Scanner(System.in);

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
}
