package ru.vsu.cs.erokhov_v_e.game;

import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int geeseAmount = 0;
        System.out.print("Введите количество гусей: 13 или 17. - ");
        while (true) {
            if (scanner.hasNextInt()) {
                geeseAmount = scanner.nextInt();
                break;
            }
            else {
                System.out.print("Неверное значение int. Повторите попытку: ");
                scanner.next();
            }
        }
        Bot foxStrategy = new Bot();
        Bot geeseStrategy = new Bot();
        FoxAndGeeseGame foxAndGeeseGame = new FoxAndGeeseGame(foxStrategy, geeseStrategy, geeseAmount);
        foxAndGeeseGame.play();
    }

}
