package com.example.shohin.finalproject;

public class Coordinate {
    private char rowCoordinate;
    private int colCoordinate;

    public Coordinate(char rowCoordinate, int colCoordinate) {
        this.rowCoordinate = rowCoordinate;
        this.colCoordinate = colCoordinate;
    }

    public char getRowCoordinate() {
        return this.rowCoordinate;
    }

    public int getColCoordinate() {
        return this.colCoordinate;
    }

    public void setRowCoordinate(char rowCoordinate) {
        this.rowCoordinate = rowCoordinate;
    }

    public void setColCoordinate(int colCoordinate) {
        this.colCoordinate = colCoordinate;
    }

    public String toString() {
        return "[" + this.rowCoordinate + ", " + this.colCoordinate + "]";
    }
}
