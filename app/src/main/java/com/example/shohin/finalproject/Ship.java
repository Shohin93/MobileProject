package com.example.shohin.finalproject;

public class Ship {
    private ShipType shipType;
    private Coordinate shipCoordinate;

    public Coordinate getShipCoordinate() {
        return shipCoordinate;
    }

    public void setShipCoordinate(Coordinate shipCoordinate) {
        this.shipCoordinate = shipCoordinate;
    }

    public ShipType getShipType() {
        return this.shipType;
    }

    public void ShipType(ShipType shipType) {
        this.shipType = shipType;
    }
}
