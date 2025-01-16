package ru.vsu.cs.erokhov_v_e.game;

public class GameEntity {
    private Node nodePosition;

    public GameEntity() {
    }

    public GameEntity(Node nodePosition) {
        this.nodePosition = nodePosition;
    }

    public Node getNode() {
        return nodePosition;
    }

    public void setNode(Node node) {
        this.nodePosition = node;
    }
}
