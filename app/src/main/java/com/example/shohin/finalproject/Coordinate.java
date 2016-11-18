package com.example.shohin.finalproject;

public class Coordinate {
    private int rowCoord;
    private char colCoord;

    public Coordinate(int rowCoord, char colCoord) {
        this.rowCoord = rowCoord;
        this.colCoord = colCoord;
    }

    public int getRowCoord() {
        return this.rowCoord;
    }

    public char getColCoord() {
        return this.colCoord;
    }

    public void setRowCoord(int rowCoord) {
        this.rowCoord = rowCoord;
    }

    public void setColCoord(char colCoord) {
        this.colCoord = colCoord;
    }

    public String toString() {
        return "[" + this.rowCoord + ", " + this.colCoord + "]";
    }
}
