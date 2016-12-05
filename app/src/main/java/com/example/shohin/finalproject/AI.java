package com.example.shohin.finalproject;

import android.content.Context;

import java.util.ArrayList;

public class AI {
    private static final int BOMBSCOUNT = 13; // bomb count includes 0
    private int numberOfBombs;

    public int getNumberOfBombs() {
        return this.numberOfBombs;
    }

    public void decrementNumberOfBombs() {
        this.numberOfBombs--;
    }
}
