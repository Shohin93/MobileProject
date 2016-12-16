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
    private float cellSize;
    private Paint paint;
    private Rect border;

    private boolean temp = false;

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
        resetShips();
        super.onSizeChanged(w, h, oldWidth, oldWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(border, paint);
        drawGridLines(canvas);
        for (Ship ship : ships) {
            ship.draw(canvas);
        }
        if(temp) {
            Paint paintCircle = new Paint();
            paintCircle.setStyle(Paint.Style.FILL);
            canvas.drawCircle(60, 138, 30, paintCircle);
        }
    }

    private void resetShips() {
        fiveCellShip = new Ship(context, this, Ship.ShipType.FiveCellShip);
        fourCellShip = new Ship(context, this, Ship.ShipType.FourCellShip);
        threeCellShip = new Ship(context, this, Ship.ShipType.ThreeCellShip);
        twoCellShip = new Ship(context, this, Ship.ShipType.TwoCellShip);
        ships[0] = fiveCellShip;
        ships[1] = fourCellShip;
        ships[2] = threeCellShip;
        ships[3] = twoCellShip;

        // Initialize AI's ships and their coordinates
//        this.aiBoard = new boolean[ROWS][COLS];
//        this.aiShips = new ArrayList<>();
//        this.aiShips.add(fiveCellShip);
//        this.aiShips.add(fourCellShip);
//        this.aiShips.add(threeCellShip);
//        this.aiShips.add(twoCellShip);
//        for (Ship ship : aiShips) {
//            Random r = new Random();
//            switch (ship.getShipType()) {
//                case FiveCellShip:
//                    boolean flag1 = true;
//                    while (flag1) {
//                        int rowPos = r.nextInt(10) + 1;
//                        int colPos = r.nextInt(10) + 1;
//                        if (ship.isShipHorizontal()) {
//                            for (int row = rowPos; row < ROWS; row++) {
//                                for (int col = colPos; col < COLS; col++) {
//                                    if (row + 5 < ROWS && row - 5 > ROWS &&
//                                            col + 5 < COLS && col - 5 > COLS &&
//                                            aiBoard[row][col] == false &&
//                                            aiBoard[row++][col] == false &&
//                                            aiBoard[row++][col] == false &&
//                                            aiBoard[row++][col] == false &&
//                                            aiBoard[row++][col] == false) {
//
//                                        aiBoard[row++][col] = true;
//                                        aiBoard[row++][col] = true;
//                                        aiBoard[row++][col] = true;
//                                        aiBoard[row++][col] = true;
//                                        aiBoard[row][col] = true;
//
//                                        ship.setCoordinates(row, col, row+5, col);
//                                        flag1 = false;
//                                    }
//                                }
//                            }
//                        }
//
//                        else {
//                            for (int row = rowPos; row < ROWS; row++) {
//                                for (int col = colPos; col < COLS; col++) {
//                                    if (row + 5 < ROWS && row - 5 > ROWS &&
//                                            col + 5 < COLS && col - 5 > COLS &&
//                                            aiBoard[row][col] == false &&
//                                            aiBoard[row][col++] == false &&
//                                            aiBoard[row][col++] == false &&
//                                            aiBoard[row][col++] == false &&
//                                            aiBoard[row][col++] == false) {
//
//                                        aiBoard[row][col++] = true;
//                                        aiBoard[row][col++] = true;
//                                        aiBoard[row][col++] = true;
//                                        aiBoard[row][col++] = true;
//                                        aiBoard[row][col] = true;
//
//                                        ship.setCoordinates(row, col, row, col+5);
//                                        flag1 = false;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    break;
//                case FourCellShip:
//                    boolean flag2 = true;
//                    while (flag2) {
//                        int rowPos = r.nextInt(10) + 1;
//                        int colPos = r.nextInt(10) + 1;
//                        if (ship.isShipHorizontal()) {
//                            for (int row = rowPos; row < ROWS; row++) {
//                                for (int col = colPos; col < COLS; col++) {
//                                    if (row + 4 < ROWS && row - 4 > ROWS &&
//                                            col + 4 < COLS && col - 4 > COLS &&
//                                            aiBoard[row][col] == false &&
//                                            aiBoard[row++][col] == false &&
//                                            aiBoard[row++][col] == false &&
//                                            aiBoard[row++][col] == false) {
//
//                                        aiBoard[row++][col] = true;
//                                        aiBoard[row++][col] = true;
//                                        aiBoard[row++][col] = true;
//                                        aiBoard[row][col] = true;
//
//                                        ship.setCoordinates(row, col, row+4, col);
//                                        flag2 = false;
//                                    }
//                                }
//                            }
//                        }
//
//                        else {
//                            for (int row = rowPos; row < ROWS; row++) {
//                                for (int col = colPos; col < COLS; col++) {
//                                    if (row + 4 < ROWS && row - 4 > ROWS &&
//                                            col + 4 < COLS && col - 4 > COLS &&
//                                            aiBoard[row][col] == false &&
//                                            aiBoard[row][col++] == false &&
//                                            aiBoard[row][col++] == false &&
//                                            aiBoard[row][col++] == false ) {
//
//                                        aiBoard[row][col++] = true;
//                                        aiBoard[row][col++] = true;
//                                        aiBoard[row][col++] = true;
//                                        aiBoard[row][col] = true;
//
//                                        ship.setCoordinates(row, col, row, col+4);
//                                        flag2 = false;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    break;
//                case ThreeCellShip:
//                    boolean flag3 = true;
//                    while (flag3) {
//                        int rowPos = r.nextInt(10) + 1;
//                        int colPos = r.nextInt(10) + 1;
//                        if (ship.isShipHorizontal()) {
//                            for (int row = rowPos; row < ROWS; row++) {
//                                for (int col = colPos; col < COLS; col++) {
//                                    if (row + 3 < ROWS && row - 3 > ROWS &&
//                                            col + 3 < COLS && col - 3 > COLS &&
//                                            aiBoard[row][col] == false &&
//                                            aiBoard[row++][col] == false &&
//                                            aiBoard[row++][col] == false) {
//
//                                        aiBoard[row++][col] = true;
//                                        aiBoard[row++][col] = true;
//                                        aiBoard[row][col] = true;
//
//                                        ship.setCoordinates(row, col, row+3, col);
//                                        flag3 = false;
//                                    }
//                                }
//                            }
//                        }
//
//                        else {
//                            for (int row = rowPos; row < ROWS; row++) {
//                                for (int col = colPos; col < COLS; col++) {
//                                    if (row + 3 < ROWS && row - 3 > ROWS &&
//                                            col + 3 < COLS && col - 3 > COLS &&
//                                            aiBoard[row][col] == false &&
//                                            aiBoard[row][col++] == false &&
//                                            aiBoard[row][col++] == false) {
//
//                                        aiBoard[row][col++] = true;
//                                        aiBoard[row][col++] = true;
//                                        aiBoard[row][col] = true;
//
//                                        ship.setCoordinates(row, col, row, col+3);
//                                        flag3 = false;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    break;
//                case TwoCellShip:
//                    boolean flag4 = true;
//                    while (flag4) {
//                        int rowPos = r.nextInt(10) + 1;
//                        int colPos = r.nextInt(10) + 1;
//                        if (ship.isShipHorizontal()) {
//                            for (int row = rowPos; row < ROWS; row++) {
//                                for (int col = colPos; col < COLS; col++) {
//                                    if (row + 2 < ROWS && row - 2 > ROWS &&
//                                            col + 2 < COLS && col - 2 > COLS &&
//                                            aiBoard[row][col] == false &&
//                                            aiBoard[row++][col] == false) {
//
//                                        aiBoard[row++][col] = true;
//                                        aiBoard[row][col] = true;
//
//                                        ship.setCoordinates(row, col, row+2, col);
//                                        flag4 = false;
//                                    }
//                                }
//                            }
//                        }
//
//                        else {
//                            for (int row = rowPos; row < ROWS; row++) {
//                                for (int col = colPos; col < COLS; col++) {
//                                    if (row + 2 < ROWS && row - 2 > ROWS &&
//                                            col + 2 < COLS && col - 2 > COLS &&
//                                            aiBoard[row][col] == false &&
//                                            aiBoard[row][col++] == false) {
//
//                                        aiBoard[row][col++] = true;
//                                        aiBoard[row][col] = true;
//
//                                        ship.setCoordinates(row, col, row, col+2);
//                                        flag4 = false;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    break;
//            }
//        }
    }

    private void drawGridLines(Canvas canvas) {
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
