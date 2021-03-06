package com.example.shohin.finalproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BattleField extends View {

    private final Context context;
    public static final int ROWS = 10;
    public static final int COLS = 10;
    public static final int PADDING = 5;

    public static int SHIP_INITIAL_YPOS;
    public static int FIVECELLSHIP_INITIAL_XPOS;
    public static int FOURCELLSHIP_INITIAL_XPOS;
    public static int THREECELLSHIP_INITIAL_XPOS;
    public static int TWOCELLSHIP_INITIAL_XPOS;

    public static Ship ships[] = new Ship[4];

    private Ship fiveCellShip;
    private Ship fourCellShip;
    private Ship threeCellShip;
    private Ship twoCellShip;
    private Ship movingShip = null;

    private boolean temp = false;
    private float cellSize;

    private Paint paint;
    private Rect border;



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (Ship ship : ships) {
                    if (ship.containsPoint((int) event.getX(), (int) event.getY())) {
                        movingShip = ship;
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (movingShip != null) {
                    movingShip.setX((int) (event.getX() - movingShip.getWidth() / 2));
                    movingShip.setY((int) (event.getY() - movingShip.getHeight() / 2));
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (movingShip != null) {
                    movingShip.attachToBoard();
                    movingShip = null;
                    invalidate();
                }
                break;
            default:
                temp = true;
                break;
        }
        return true;
    }

    public BattleField(Context context, AttributeSet attributes) {
        super(context, attributes);
        this.context = context;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldWidth, int oldHeight) {
        this.border = new Rect(PADDING, PADDING, w - PADDING, w - PADDING);
        this.cellSize = (float) (border.width() / 10.0);
        initialShipPosition();
        resetShipsAndCoordinates();
        super.onSizeChanged(w, h, oldWidth, oldWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(border, paint);
        drawBoardLines(canvas);
        for (Ship ship : ships) {
            ship.draw(canvas);
        }
        if(temp) {
            Paint paintCircle = new Paint();
            paintCircle.setStyle(Paint.Style.FILL);
            canvas.drawCircle(60, 138, 30, paintCircle);
        }
    }

    private void resetShipsAndCoordinates() {
        fiveCellShip = new Ship(context, this, Ship.ShipType.FiveCellShip);
        fourCellShip = new Ship(context, this, Ship.ShipType.FourCellShip);
        threeCellShip = new Ship(context, this, Ship.ShipType.ThreeCellShip);
        twoCellShip = new Ship(context, this, Ship.ShipType.TwoCellShip);
        ships[0] = fiveCellShip;
        ships[1] = fourCellShip;
        ships[2] = threeCellShip;
        ships[3] = twoCellShip;

        // Set up initial coordinates
        ships[0].coordinates.setCoords(1, 1, 6, 1);
        ships[1].coordinates.setCoords(1, 3, 5, 3);
        ships[2].coordinates.setCoords(1, 5, 4, 5);
        ships[3].coordinates.setCoords(1, 7, 3, 7);
    }

    private void drawBoardLines(Canvas canvas) {
        for (int col = 1; col < COLS; col++) {
            canvas.drawLine(PADDING + cellSize * col, PADDING,
                    PADDING + cellSize * col, PADDING + border.height(), paint);
        }
        for (int row = 1; row < ROWS; row++) {
            canvas.drawLine(PADDING, PADDING + cellSize * row,
                    PADDING + border.height(), PADDING + cellSize * row, paint);
        }
    }

    // Set initial positions for ships on the board
    private void initialShipPosition() {
        SHIP_INITIAL_YPOS = 2;
        FIVECELLSHIP_INITIAL_XPOS = (PADDING) + 1;
        FOURCELLSHIP_INITIAL_XPOS = (int) (PADDING + cellSize * 2) + 1;
        THREECELLSHIP_INITIAL_XPOS = (int) (PADDING + cellSize * 4) + 1;
        TWOCELLSHIP_INITIAL_XPOS = (int) (PADDING + cellSize * 6) + 1;
    }

    public float getCellSize() {
        return cellSize;
    }
}
