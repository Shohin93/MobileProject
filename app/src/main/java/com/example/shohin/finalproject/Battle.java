package com.example.shohin.finalproject;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Battle extends AppCompatActivity {

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static char[][] aiBoard = new char[10][10];
    private static int[] ships = {2, 3, 4, 5};

    TableRow tableRow;
    TableLayout tableLayout;
    TextView battleTitle, myHits, aiHits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        init();
        addShips();
        print();
        Log.d("this is my array", "arr: " + Arrays.toString(aiBoard));

        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Top Secret.ttf");
        battleTitle = (TextView) findViewById(R.id.battleTitle);
        myHits = (TextView) findViewById(R.id.myHitsText);
        aiHits = (TextView) findViewById(R.id.aiHitsText);
        battleTitle.setTypeface(customFont);
        myHits.setTypeface(customFont);
        aiHits.setTypeface(customFont);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        for (int row = 0; row < ROWS; row++) {
            tableRow = new TableRow(this);
            for (int col = 0; col < COLS; col++) {
                final TextView textView = new TextView(this);
                textView.setId(View.generateViewId());
                textView.setBackground(getResources().getDrawable(R.drawable.border));
                textView.setTextSize(30);
                textView.setGravity(Gravity.CENTER);
                textView.setHeight(95);
                textView.setWidth(98);
                textView.setId(Integer.parseInt(row + "" + col));
                textView.setClickable(true);
                tableRow.addView(textView);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Battle.this, "Clicked", Toast.LENGTH_LONG);
                        System.out.println("Cell clicked: " + textView.getId());
                        textView.setText("X");

                        // Toast.makeText(Battle.this, "FiveCellShip position is " + BattleField.ships[0].coordinates.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            tableLayout.addView(tableRow, new TableLayout.LayoutParams
                    (TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    private static void init() {
        for(int i = 0; i < aiBoard.length; i++) {
            for(int j = 0; j < aiBoard[0].length; j++) {
                aiBoard[i][j] = 'O';
            }
        }
    }

    private static void addShips() {
        for(int ship: ships) {
            System.out.println("Adding ship " + ship);
            boolean added = false;
            while(!added) {
                int x = (int)(aiBoard.length * Math.random());
                int y = (int)(aiBoard[0].length * Math.random());
                boolean vertical = ((int)(10 * Math.random())) % 2 == 0;
                if(vertical) {
                    // Check for vertical space
                    boolean hasSpace = true;
                    for(int i = 0; i < ship; i++) {
                        if(y + i >= aiBoard[0].length) {
                            hasSpace = false;
                            break;
                        }
                        if(aiBoard[x][y+i] != 'O') {
                            hasSpace = false;
                            break;
                        }
                    }
                    if(!hasSpace) {
                        // No room there, check again
                        continue;
                    }
                    for(int i = 0; i < ship; i++) {
                        aiBoard[x][y+i] = 'X';
                    }
                    added = true;
                } else {
                    // Check for horizontal space
                    boolean hasSpace = true;
                    for(int i = 0; i < ship; i++) {
                        if(x + i >= aiBoard.length) {
                            hasSpace = false;
                            break;
                        }
                        if(aiBoard[x+i][y] != 'O') {
                            hasSpace = false;
                            break;
                        }
                    }
                    if(!hasSpace) {
                        // No room there, check again
                        continue;
                    }
                    for(int i = 0; i < ship; i++) {
                        aiBoard[x+i][y] = 'X';
                    }
                    added = true;
                }
            }
        }
    }
    private static void print() {
        for(int i = 0; i < aiBoard.length; i++) {
            for(int j = 0; j < aiBoard[0].length; j++) {
                System.out.print(""+aiBoard[i][j]);
            }
            System.out.println("");
        }
    }
}
