package com.example.shohin.finalproject;

public class Coordinate {
    private int x1, y1,
                x2, y2;

    public Coordinate(int x1Coord, int y1Coord, int x2Coord, int y2Coord) {
        this.x1 = x1Coord;
        this.y1 = y1Coord;
        this.x2 = x2Coord;
        this.y2 = y2Coord;
    }

    public int getX1() {
        return this.x1;
    }

    public int getY1() {
        return this.y1;
    }

    public int getX2() {
        return this.x2;
    }

    public int getY2() {
        return this.y2;
    }

    public void setCoords(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public String toString() {
        return "[(" + this.x1 + ", " + this.y1 + "); " +
                "(" + this.x2 + ", " + this.y2 + "]";
    }
}
