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

        Factory[] factories = new Factory[]{
                Bot.FACTORY,
                KeyBoardPlayer.FACTORY
        };

        Strategy[] players = new Strategy[2];
        for (int i = 0; i < 2; i++) {
            players[i] = playerChoose(factories);
        }

        FoxAndGeeseGame foxAndGeeseGame = new FoxAndGeeseGame(players[0],players[1],geeseAmount);
        foxAndGeeseGame.play();
    }

    public static Strategy playerChoose(Factory[] factories) {
        StringBuilder output = new StringBuilder("Выберите тип игрока \n");
        for (int i = 0; i < factories.length; i++) {
            output.append(i).append(". ").append(factories[i].create()).append("\n");
        }
        System.out.println(output);
        Scanner in = new Scanner(System.in);
        Strategy player;
        try {
            player = factories[in.nextInt()].create();
        } catch (Exception e) {
            System.out.println("Напишите целое число от 1 до 2 без знаков препинания и пробелов");
            return playerChoose(factories);
        }
        return player;
    }
}
