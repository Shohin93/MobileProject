package com.example.shohin.finalproject;

public enum ShipType {
    FiveCellShip(5), FourCellShip(4),
    ThreeCellShip(3), TwoCellShip(2),
    OneCellShip(1);

    private int shipSize;

    private ShipType(int shipSize) {
        this.shipSize = shipSize;
    }

    public int getShipSize() {
        return this.shipSize;
    }
}
