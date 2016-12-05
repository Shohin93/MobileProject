package com.example.shohin.finalproject;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class AI {
    private static final int BOMBSCOUNT = 13; // bomb count includes 0
    private ArrayList<Coordinate> aiCoordinates;
    private int numberOfBombs;

    public AI() {
        this.numberOfBombs = BOMBSCOUNT;
        this.aiCoordinates = new ArrayList<>();
    }

    public ArrayList<Coordinate> getAiCoordinates() {
        return this.aiCoordinates;
    }

    public int getNumberOfBombs() {
        return this.numberOfBombs;
    }

    public void decrementNumberOfBombs() {
        this.numberOfBombs--;
    }

    // Mainly for testing purposes
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Coordinate c: aiCoordinates) {
            sb.append(c.toString() + "\n");
        }
        return sb.toString();
    }
}
