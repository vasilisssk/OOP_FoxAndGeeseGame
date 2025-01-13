package ru.vsu.cs.erokhov_v_e.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private Status status;
    private Point coordinates;
    private List<Node> connections = new ArrayList<>();

    public Node(Point coordinates, Status status) {
        this.coordinates = coordinates;
        this.status = status;
    }

    public void connect(Node node) {
        connections.add(node);
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Node> getConnections() {
        return new ArrayList<>(connections);
    }

    public void setConnections(List<Node> connections) {
        this.connections = new ArrayList<>(connections);
    }

    @Override
    public String toString() {
        return coordinates.x + "," + coordinates.y;
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