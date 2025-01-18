package ru.vsu.cs.erokhov_v_e.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private NodeStatus status;
    private Coordinate coordinates;
    private List<Node> connections = new ArrayList<>();

    public Node(Coordinate coordinates, NodeStatus status) {
        this.coordinates = coordinates;
        this.status = status;
    }

    public void connect(Node node) {
        connections.add(node);
    }

    public Coordinate getCoordinate() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public NodeStatus getStatus() {
        return status;
    }

    public void setStatus(NodeStatus status) {
        this.status = status;
    }

    public List<Node> getConnections() {
        return List.copyOf(connections);
    }

    public void setConnections(List<Node> connections) {
        this.connections = List.copyOf(connections);
    }

    @Override
    public String toString() {
        return coordinates.getX() + "," + coordinates.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return coordinates.equals(node.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }
}