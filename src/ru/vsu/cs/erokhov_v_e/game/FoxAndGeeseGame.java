package ru.vsu.cs.erokhov_v_e.game;

import java.util.ArrayList;
import java.util.List;

public class FoxAndGeeseGame implements CalculateMoves{
    private Strategy foxStrategy;
    private Strategy geeseStrategy;
    private boolean foxTurn = true;
    private boolean gameIsOver = false;
    private GameField gameField = new GameField();
    private Fox fox = new Fox();
    private List<Goose> geese = new ArrayList<>();
    private String winner = "";
    private int geeseAmount;
    private int globalCounter = 0;

    public FoxAndGeeseGame(Strategy foxStrategy, Strategy geeseStrategy, int geeseAmount) {
        this.foxStrategy = foxStrategy;
        this.geeseStrategy = geeseStrategy;
        this.geeseAmount = geeseAmount;
    }

    public void play() {
        placeGeese(geeseAmount);
        System.out.println("\nИгровое поле после расстановки гусей:");
        gameField.displayGameFieldMap();

        foxStrategy.placeFox(fox, gameField);

        System.out.println("\n|--Команды для хода:--|\n|U /u  - вверх        |\n|UR/ur - вверх, вправо|\n|R /r  - вправо       |\n|DR/dr - вниз, вправо |" +
                "\n|D /d  - вниз         |\n|DL/dl - вниз, влево  |\n|L /l  - влево        |\n|UL/ul - вверх, влево |");

        System.out.println("\nИгровое поле после расстановки лисы:");
        gameField.displayGameFieldMap();

        System.out.println("\nПервый ход делает лиса!");

        gameLoop:
        while (!gameIsOver) {
            if (foxTurn) {
                foxTurn = !foxTurn;
                if (foxStrategy.moveFox(fox,geese,this)) {
                    foxTurn = !foxTurn;
                }

                System.out.println("\nИгровое поле после хода лисы:");
                gameField.displayGameFieldMap();
            }

            globalCounter++;
            checkGameStatus();
            if (gameIsOver) {
                System.out.println("Игра завершена в "+globalCounter+" ходов. Победитель" + winner + ".");
                break;
            }

            if (!foxTurn) {
                geeseStrategy.moveGoose(geese,this);
                foxTurn = !foxTurn;
                System.out.println("\nИгровое поле после хода гуся:");
                gameField.displayGameFieldMap();
            }

            globalCounter++;
            checkGameStatus();
            if (gameIsOver) {
                System.out.println("Игра завершена в "+globalCounter+" ходов. Победитель" + winner + ".");
                break;
            }
        }
    }

    public void checkGameStatus() {
        if (geese.size() <= 5) {
            gameIsOver = !gameIsOver;
            winner += " Лиса";
            return;
        }
        int x = fox.getNode().getCoordinate().getX();
        int y = fox.getNode().getCoordinate().getY();
        boolean geeseBlockedFox = true;
        List<Node> foxNodeConnections = fox.getNode().getConnections();
        upperLoop:
        for (Node node1 : foxNodeConnections) {
            if (node1.getStatus() != NodeStatus.GOOSE) {
                geeseBlockedFox = false;
                break upperLoop;
            }
            for (Node node2 : node1.getConnections()) {
                if ((Math.abs(node2.getCoordinate().getX()-x) == 0 && Math.abs(node2.getCoordinate().getY()-y) == 2) ||
                        (Math.abs(node2.getCoordinate().getX()-x) == 2 && Math.abs(node2.getCoordinate().getY()-y) == 0) ||
                        (Math.abs(node2.getCoordinate().getX()-x) == 2 && Math.abs(node2.getCoordinate().getY()-y) == 2)) {
                    if (node2.getStatus() != NodeStatus.GOOSE) {
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

    public void placeGeese(int geeseAmount) {
        if (geeseAmount == 17) {
            for (int i = 4; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    // по ключу находим node и добавляем в goose этот node
                    Coordinate coordinate = new Coordinate(j,i);
                    if (gameField.getGameFieldMap().containsKey(coordinate)) {
                        Node node = gameField.getGameFieldMap().get(coordinate);
                        node.setStatus(NodeStatus.GOOSE);
                        Goose goose = new Goose(node);
                        geese.add(goose);
                    }
                }
            }
            for (int i = 0; i <= 6; i += 6) {
                for (int j = 2; j < 4; j++) {
                    Coordinate coordinate = new Coordinate(i,j);
                    if (gameField.getGameFieldMap().containsKey(coordinate)) {
                        Node node = gameField.getGameFieldMap().get(coordinate);
                        node.setStatus(NodeStatus.GOOSE);
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
                    Coordinate coordinate = new Coordinate(j,i);
                    if (gameField.getGameFieldMap().containsKey(coordinate)) {
                        Node node = gameField.getGameFieldMap().get(coordinate);
                        node.setStatus(NodeStatus.GOOSE);
                        Goose goose = new Goose(node);
                        geese.add(goose);
                    }
                }
            }
        }
    }
    @Override
    public Boolean calculateFoxMove(Movement movement, Fox fox, List<Goose> geese) {
        if (movement == null) {
            return false;
        }
        int counter = 0;
        Node foxNode = fox.getNode();
        List<Node> foxNodeConnections = foxNode.getConnections();
        Coordinate foxCoordinate = foxNode.getCoordinate();
        Coordinate goalCoordinate = fox.getNode().getCoordinate().get(movement);
        for (int i = 0; i < foxNodeConnections.size(); i++) {
            if (!foxNodeConnections.get(i).getCoordinate().equals(goalCoordinate)) {
                counter++;
            }
        }
        if (counter == foxNodeConnections.size()) {
            return false;
        }
        Node connectedNode = null;
        for (int i = 0; i < foxNode.getConnections().size(); i++) {
            connectedNode = foxNode.getConnections().get(i);
            if (connectedNode.getCoordinate().equals(goalCoordinate)) {
                break;
            }
        }

        if (connectedNode.getStatus() == NodeStatus.EMPTY) {
            foxNode.setStatus(NodeStatus.EMPTY);
            fox.setNode(connectedNode);
            connectedNode.setStatus(NodeStatus.FOX);
            return true;
        }

        if (connectedNode.getStatus() == NodeStatus.GOOSE) {
            Coordinate eatGooseCoordinate = foxCoordinate.get(movement, 2);
            Node eatGooseNode;
            for (int i = 0; i < connectedNode.getConnections().size(); i++) {
                eatGooseNode = connectedNode.getConnections().get(i);
                if (eatGooseNode.getCoordinate().equals(eatGooseCoordinate)) {
                    if (eatGooseNode.getStatus() == NodeStatus.GOOSE) {
                        System.out.println("Можно съесть гуся, если только по итогу хода вы окажитесь на свободной клетке!");
                        return false;
                    }
                    foxNode.setStatus(NodeStatus.EMPTY);
                    fox.setNode(eatGooseNode);
                    eatGooseNode.setStatus(NodeStatus.FOX);
                    eatGoose(connectedNode, geese);
                    System.out.println("Лиса съела гуся по координатам: x=" + goalCoordinate.getX() + ", y=" + goalCoordinate.getY() + ". Гусей осталось: " + geese.size() + ". Ходит снова лиса.");
                    return null;
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
        node.setStatus(NodeStatus.EMPTY);
    }

    @Override
    public boolean calculateGooseMove(Movement movement, Goose goose) {
        if (movement == null) {
            return false;
        }
        int counter = 0;
        Node gooseNode = goose.getNode();
        List<Node> gooseNodeConnections = gooseNode.getConnections();
        Coordinate gooseCoordinate = gooseNode.getCoordinate();
        Coordinate goalCoordinate = gooseCoordinate.get(movement);
        for (int i = 0; i < gooseNodeConnections.size(); i++) {
            if (!gooseNodeConnections.get(i).getCoordinate().equals(goalCoordinate)) {
                counter++;
            }
        }
        if (counter == gooseNodeConnections.size()) {
            return false;
        }
        Node connectedNode = null;
        for (int i = 0; i < gooseNodeConnections.size(); i++) {
            connectedNode = gooseNodeConnections.get(i);
            if (connectedNode.getCoordinate().equals(goalCoordinate)) {
                break;
            }
        }
        if (connectedNode.getStatus() == NodeStatus.EMPTY) {
            gooseNode.setStatus(NodeStatus.EMPTY);
            goose.setNode(connectedNode);
            connectedNode.setStatus(NodeStatus.GOOSE);
            return true;
        }
        return false;
    }
}
