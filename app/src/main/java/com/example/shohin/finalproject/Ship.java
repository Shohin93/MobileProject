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

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

    private int x;
    private int y;
    private int size;
    private boolean placedHorizontal;
    private ShipType shipType;
    private Orientation orientation;
    private BitmapDrawable bm_horizontal;
    private BitmapDrawable bm_vertical;
    private float cellSize;
    private Paint paint;
    private Context context;

    public Ship(Context context, BattleField grid, ShipType type) {
        this.context = context;
        this.shipType = type;
        this.cellSize = grid.getCellSize();
        this.paint = new Paint();
        this.placedHorizontal = false;
        switch (type) {
            case FiveCellShip:
                size = 5;
                x = BattleField.FIVECELLSHIP_INITIAL_XPOS;
                break;
            case FourCellShip:
                size = 4;
                x = BattleField.FOURCELLSHIP_INITIAL_XPOS;
                break;
            case ThreeCellShip:
                size = 3;
                x = BattleField.THREECELLSHIP_INITIAL_XPOS;
                break;
            case TwoCellShip:
                size = 2;
                x = BattleField.TWOCELLSHIP_INITIAL_XPOS;
                break;
        }
        this.y = BattleField.SHIP_INITIAL_YPOS;
        this.orientation = Orientation.VERTICAL;
        this.bm_horizontal = shipBitmap(shipType);
        this.bm_horizontal = attachBitmapToGrid(bm_horizontal);
        this.bm_vertical = rotateBitmap(bm_horizontal);
    }

    public void draw(Canvas canvas) {
        BitmapDrawable bitmap = (BitmapDrawable) valueByOrientation(bm_horizontal, bm_vertical);
        canvas.drawBitmap(bitmap.getBitmap(), x, y, paint);
    }

    // Checks if x and y are in the range
    public boolean containsPoint(int x, int y) {
        return (x > this.x && x < this.x + getWidth() &&
                y > this.y && y < this.y + getHeight());
    }

    // When dragging/moving a ship around, the method attaches it to grid when touch is released
    public void attachToGrid() {
        this.x = (int)(((x / (int)cellSize) * cellSize));
        this.y = (int)(((y / (int)cellSize) * cellSize));

        // For testing purposes
        Toast.makeText(context, "(x,y) = " + (int)(Math.ceil(x/cellSize) + 1) + "," + (int)(Math.ceil(y/cellSize) + 1) + "", Toast.LENGTH_SHORT).show();
    }

    public int getWidth() {
        return ((BitmapDrawable)valueByOrientation(bm_horizontal, bm_vertical))
                .getBitmap().getWidth();
    }

    public int getHeight() {
        return ((BitmapDrawable)valueByOrientation(bm_horizontal, bm_vertical))
                .getBitmap().getHeight();
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }


    private BitmapDrawable shipBitmap(ShipType type) {
        switch(type) {
            case TwoCellShip:
            default:
                return new BitmapDrawable(
                        BitmapFactory.decodeResource(context.getResources(), R.drawable.ship));
        }
    }

    private BitmapDrawable rotateBitmap(BitmapDrawable bitmap) {
        Matrix matrix = new Matrix();
        Bitmap originalBitmap = bitmap.getBitmap();
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        matrix.postRotate(90);
        Bitmap rotatedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, width, height, matrix, true);
        return new BitmapDrawable(rotatedBitmap);
    }

    private BitmapDrawable attachBitmapToGrid(BitmapDrawable bitmap) {
        Bitmap originalBitmap = bitmap.getBitmap();
        Matrix matrix = new Matrix();
        int height = originalBitmap.getHeight();
        int width = originalBitmap.getWidth();
        float newWidth = cellSize * size;
        float newHeight = cellSize;
        matrix.postScale(newWidth / width, newHeight / height);
        Bitmap newBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, width, height, matrix, true);
        return new BitmapDrawable(newBitmap);
    }

    private Object valueByOrientation(Object horizontal, Object vertical) {
        if(orientation == Orientation.HORIZONTAL) {
            return horizontal;
        } else {
            return vertical;
        }
    }
}
