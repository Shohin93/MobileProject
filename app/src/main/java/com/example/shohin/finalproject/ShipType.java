package com.example.shohin.finalproject;

public enum ShipType {
    FiveCellShip(5), FourCellShip(4),
    ThreeCellShip(3), TwoCellShip(2);

    private int shipSize;

    ShipType(int shipSize) {
        this.shipSize = shipSize;
    }

    public int getShipSize() {
        return this.shipSize;
    }
}
