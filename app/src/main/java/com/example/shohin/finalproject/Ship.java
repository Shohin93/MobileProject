package com.example.shohin.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Matrix;
import android.widget.Toast;

public class Ship {

    public enum ShipType {
        FiveCellShip, FourCellShip,
        ThreeCellShip, TwoCellShip
    }

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

    public Coordinate coordinates;

    private int x;
    private int y;
    private int size;
    private float cellSize;
    private boolean placedHorizontal;
    private ShipType shipType;

    private Orientation orientation;
    private BitmapDrawable bm_horizontal;
    private BitmapDrawable bm_vertical;
    private Paint paint;
    private Context context;

    public Ship(Context context, BattleField board, ShipType shipType) {
        this.context = context;
        this.shipType = shipType;
        this.cellSize = board.getCellSize();
        this.paint = new Paint();
        this.placedHorizontal = false;
        this.y = BattleField.SHIP_INITIAL_YPOS;

        switch (shipType) {
            case FiveCellShip:
                size = 5;
                x = BattleField.FIVECELLSHIP_INITIAL_XPOS;
                this.coordinates = new Coordinate(x, y, x, y + size);
                break;
            case FourCellShip:
                size = 4;
                x = BattleField.FOURCELLSHIP_INITIAL_XPOS;
                this.coordinates = new Coordinate(x, y, x, y + size);
                break;
            case ThreeCellShip:
                size = 3;
                x = BattleField.THREECELLSHIP_INITIAL_XPOS;
                this.coordinates = new Coordinate(x, y, x, y + size);
                break;
            case TwoCellShip:
                size = 2;
                x = BattleField.TWOCELLSHIP_INITIAL_XPOS;
                this.coordinates = new Coordinate(x, y, x, y + size);
                break;
        }
        this.orientation = Orientation.VERTICAL;
        this.bm_horizontal = shipBitmap(shipType);
        this.bm_horizontal = attachBitmapToBoard(bm_horizontal);
        this.bm_vertical = rotateBitmap(bm_horizontal);
    }

    public void draw(Canvas canvas) {
        BitmapDrawable bitmap = (BitmapDrawable) orientation(bm_horizontal, bm_vertical);
        canvas.drawBitmap(bitmap.getBitmap(), x, y, paint);
    }

    // Checks if x and y are in the range
    public boolean containsPoint(int x, int y) {
        return (x > this.x && x < this.x + getWidth() &&
                y > this.y && y < this.y + getHeight());
    }

    // When dragging/moving a ship around, the method attaches it to board when touch is released
    public void attachToBoard() {
        this.x = (int)(((x / (int)cellSize) * cellSize));
        this.y = (int)(((y / (int)cellSize) * cellSize));

        int shipsX = (int)(Math.ceil(y / cellSize) + 1);
        int shipsY = (int)(Math.ceil(x / cellSize) + 1);

        switch(this.shipType) {
            case FiveCellShip:
                this.coordinates.setCoords(shipsX, shipsY, shipsX + 5, shipsY);
                break;
            case FourCellShip:
                this.coordinates.setCoords(shipsX, shipsY, shipsX + 4, shipsY);
                break;
            case ThreeCellShip:
                this.coordinates.setCoords(shipsX, shipsY, shipsX + 3, shipsY);
                break;
            case TwoCellShip:
                this.coordinates.setCoords(shipsX, shipsY, shipsX + 2, shipsY);
                break;
        }
    }

    public int getWidth() {
        return ((BitmapDrawable) orientation(bm_horizontal, bm_vertical))
                .getBitmap().getWidth();
    }

    public int getHeight() {
        return ((BitmapDrawable) orientation(bm_horizontal, bm_vertical))
                .getBitmap().getHeight();
    }

    private Object orientation(Object horizontal, Object vertical) {
        if(orientation == Orientation.HORIZONTAL) {
            return horizontal;
        } else {
            return vertical;
        }
    }

    public BitmapDrawable shipBitmap(ShipType type) {
        switch(type) {
            case TwoCellShip:
            default:
                return new BitmapDrawable(
                        BitmapFactory.decodeResource(context.getResources(), R.drawable.ship));
        }
    }

    public BitmapDrawable rotateBitmap(BitmapDrawable bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap bm = bitmap.getBitmap();
        int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap rotatedBm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return new BitmapDrawable(rotatedBm);
    }

    private BitmapDrawable attachBitmapToBoard(BitmapDrawable bitmap) {
        Bitmap bm = bitmap.getBitmap();
        Matrix matrix = new Matrix();
        int height = bm.getHeight();
        int width = bm.getWidth();
        float newWidth = cellSize * size;
        float newHeight = cellSize;
        matrix.postScale(newWidth / width, newHeight / height);
        Bitmap newBm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return new BitmapDrawable(newBm);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinates(int x1, int y1, int x2, int y2) {
        this.coordinates.setCoords(x1, y1, x2, y2);
    }

    public ShipType getShipType() {
        return this.shipType;
    }

    public void setShipPosition(boolean changedPos) {
        this.placedHorizontal = changedPos;
    }

    public boolean isShipHorizontal() {
        return this.placedHorizontal;
    }
}
