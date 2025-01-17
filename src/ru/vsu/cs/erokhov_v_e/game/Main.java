package ru.vsu.cs.erokhov_v_e.game;

import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int geeseAmount;
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
        Bot bot1 = new Bot();
        Bot bot2 = new Bot();
        KeyBoardPlayer keyBoardPlayer1 = new KeyBoardPlayer();
        KeyBoardPlayer keyBoardPlayer2 = new KeyBoardPlayer();
        FoxAndGeeseGame foxAndGeeseGame = new FoxAndGeeseGame(bot1, keyBoardPlayer2, geeseAmount);
        foxAndGeeseGame.play();
    }

}
