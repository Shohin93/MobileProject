package com.example.shohin.finalproject;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
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

    private Ship ships[] = new Ship[4];
    private Ship fiveCellShip;
    private Ship fourCellShip;
    private Ship threeCellShip;
    private Ship twoCellShip;
    private Ship movingShip = null;
    private float cellSize;
    private Paint paint;
    private Rect border;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < ships.length; i++) {
                    if (ships[i].containsPoint((int)event.getX(), (int)event.getY())) {
                        movingShip = ships[i];
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (movingShip != null) {
                    movingShip.setX((int)(event.getX() - movingShip.getWidth() / 2));
                    movingShip.setY((int)(event.getY() - movingShip.getHeight() / 2));
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (movingShip != null) {
                    movingShip.attachToGrid();
                    movingShip = null;
                    invalidate();
                }
                break;
        }
        return true;
    }

    public BattleField(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.border = new Rect(PADDING, PADDING, w - PADDING, w - PADDING);
        this.cellSize = (float) (border.width() / 10.0);
        initialShipPosition();
        resetShips();
        super.onSizeChanged(w, h, oldw, oldh);
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(border, paint);
        drawGridLines(canvas);
        for (Ship ship : ships) {
            ship.draw(canvas);
        }
    }

    private void resetShips() {
        fiveCellShip = new Ship(context, this, ShipType.FiveCellShip);
        fourCellShip = new Ship(context, this, ShipType.FourCellShip);
        threeCellShip = new Ship(context, this, ShipType.ThreeCellShip);
        twoCellShip = new Ship(context, this, ShipType.TwoCellShip);
        ships[0] = fiveCellShip;
        ships[1] = fourCellShip;
        ships[2] = threeCellShip;
        ships[3] = twoCellShip;
    }

    private void drawGridLines(Canvas canvas) {
        for (int col = 1; col < COLS; col++) {
            canvas.drawLine(PADDING + cellSize * col, PADDING,
                    PADDING + cellSize * col, PADDING + border.height(), paint);
//            if(col == 1) {
//                Paint text = new Paint();
//                text.setColor(Color.WHITE);
//                text.setTextAlign(Paint.Align.CENTER);
//                AssetManager am = getResources().getAssets();
//                Typeface customFont = Typeface.createFromAsset(am, "fonts/Top Secret.ttf");
//                text.setTypeface(customFont);
//                text.setTextSize(65);
//                int xPos = (canvas.getWidth() / 2);
//                int yPos = (int) ((canvas.getHeight() / 2) - ((text.descent() + text.ascent()) / 2)) ;
//                canvas.drawText("1", 55, 70, text);
//            }
        }
        for (int row = 1; row < ROWS; row++) {
            canvas.drawLine(PADDING, PADDING + cellSize * row,
                    PADDING + border.height(), PADDING + cellSize * row, paint);
        }
    }

    private void initialShipPosition() {
        SHIP_INITIAL_YPOS = 2;
        FIVECELLSHIP_INITIAL_XPOS = (PADDING);
        FOURCELLSHIP_INITIAL_XPOS = (int) (PADDING + cellSize * 2);
        THREECELLSHIP_INITIAL_XPOS = (int) (PADDING + cellSize * 4);
        TWOCELLSHIP_INITIAL_XPOS = (int) (PADDING + cellSize * 6);
    }

    public float getCellSize() {
        return cellSize;
    }
}
