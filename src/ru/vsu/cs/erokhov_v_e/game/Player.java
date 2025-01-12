package ru.vsu.cs.erokhov_v_e.game;

public class Player {
    private Node nodePosition;

    public Player() {
    }

    public Player(Node nodePosition) {
        this.nodePosition = nodePosition;
    }

    public int getXShift(String flag) {
        int xShift = 0;
        if (flag.equals("R") || flag.equals("UR") || flag.equals("DR")) {
            xShift = 1;
        }
        else if (flag.equals("L") || flag.equals("UL") || flag.equals("DL")) {
            xShift = -1;
        }
        return xShift;
    }

    public int getYShift(String flag) {
        int yShift = 0;
        if (flag.equals("U") || flag.equals("UL") || flag.equals("UR")) {
            yShift = -1;
        }
        else if (flag.equals("D") || flag.equals("DL") || flag.equals("DR")) {
            yShift = 1;
        }
        return yShift;
    }

    public void move(String flag) {};


//    public void move(String flag) {
//        int xShift = 0;
//        int yShift = 0;
//        switch (flag) {
//            case "U": {
//                yShift = -1;
//                break;
//            }
//            case "D": {
//                yShift = 1;
//                break;
//            }
//            case "R": {
//                xShift = 1;
//                break;
//            }
//            case "L": {
//                xShift = -1;
//                break;
//            }
//            case "UR": {
//                xShift = 1;
//                yShift = -1;
//                break;
//            }
//            case "UL": {
//                xShift = -1;
//                yShift = -1;
//                break;
//            }
//            case "DR": {
//                xShift = 1;
//                yShift = 1;
//                break;
//            }
//            case "DL": {
//                xShift = -1;
//                yShift = 1;
//                break;
//            }
//        }
//        // учитываем флаг, получаем новые координаты, проверяем есть ли они в мапе или эта клетка пуста (status == null), если да, то
//        // перемещаемся туда, если нет то выводим, что такой ход не может быть произведен (мб возвращать boolean чтобы проверять возможность хода)
//    }

    public Node getNode() {
        return nodePosition;
    }

    public void setNode(Node node) {
        this.nodePosition = node;
    }
}
