package ru.vsu.cs.erokhov_v_e.game;

public class Player {
    private Node nodePosition;

    public Player() {
    }

    public Player(Node nodePosition) {
        this.nodePosition = nodePosition;
    }

    public void move(String flag) {
        int xShift = 0;
        int yShift = 0;
        switch (flag) {
            case "U" -> {
                yShift = -1;
            }
            case "D" -> {
                yShift = 1;
            }
            case "R" -> {
                xShift = 1;
            }
            case "L" -> {
                xShift = -1;
            }
            case "UR" -> {
                xShift = 1;
                yShift = -1;
            }
            case "UL" -> {
                xShift = -1;
                yShift = -1;
            }
            case "DR" -> {
                xShift = 1;
                yShift = 1;
            }
            case "DL" -> {
                xShift = -1;
                yShift = 1;
            }
        }
        // учитываем флаг, получаем новые координаты, проверяем есть ли они в мапе или эта клетка пуста (status == null), если да, то
        // перемещаемся туда, если нет то выводим, что такой ход не может быть произведен (мб возвращать boolean чтобы проверять возможность хода)
    }

    public Node getNode() {
        return nodePosition;
    }

    public void setNode(Node node) {
        this.nodePosition = node;
    }
}
