package ru.vsu.cs.erokhov_v_e;

import java.awt.*;
import java.util.ArrayList;

public class Node {
    //private Status status;
    private Player player;
    private Point coordinates;
    private ArrayList<Node> connections = new ArrayList<>();

    public Node(Point coordinates, Player player/*Status status*/) {
        this.coordinates = coordinates;
        this.player = player;
        //this.status = status;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    //    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }

    public ArrayList<Node> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Node> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return coordinates.x + "," + coordinates.y;
    }
}
