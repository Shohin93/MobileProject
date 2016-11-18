package com.example.shohin.finalproject;

import android.graphics.Color;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class NewGame extends AppCompatActivity {
    private static final int ROWS = 11;
    private static final int COLS = 11;

    TableLayout tableLayout;
    TableRow tableRow;
    TextView textView, placeShipsText;
    Button startBattle;

    char asciiChar = 65;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        // Change the font of the button and title text
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Top Secret.ttf");
        startBattle = (Button) findViewById(R.id.startBattle);
        startBattle.setTypeface(customFont);
        placeShipsText = (TextView) findViewById(R.id.placeShipsText);
        placeShipsText.setTypeface(customFont);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        // Fill out the table with with empty cells
        tableLayout = fillTableLayout(tableLayout);
        // Fill out the table with initial ships
        tableLayout = populateWithShips(tableLayout);
    }

    // Fill out the table with empty cells
    private TableLayout fillTableLayout(TableLayout tableLayout) {
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Top Secret.ttf");
        for(int i = 1; i <= ROWS; i++) {
            tableRow = new TableRow(this);
            for(int j = 1; j <= COLS; j++) {
                textView = new TextView(this);
                textView.setId(View.generateViewId());
                textView.setBackground(getResources().getDrawable(R.drawable.border));

                // Set digits on board
                if(i == 1 && j != 1) {
                    textView.setText(Integer.toString(j - 1));
                }
                // Set letters on board
                if(i != 1 && j == 1) {
                    textView.setText(Character.toString(asciiChar++));
                }
                textView.setTypeface(customFont);
                textView.setTextSize(20);
                textView.setGravity(Gravity.CENTER);
                textView.setHeight(85);
                textView.setWidth(89);
                tableRow.addView(textView);
            }
            tableLayout.addView(tableRow, new TableLayout.LayoutParams
                    (TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
        }
        return tableLayout;
    }

    // Fill out the table with initial ships
    private TableLayout populateWithShips(TableLayout tableLayout) {
        Ship fiveCellShip = new Ship();
        Ship fourCellShip = new Ship();
        Ship threeCellShip = new Ship();
        Ship twoCellShip = new Ship();
        Ship oneCellShip = new Ship();

        for(int i = 1; i <= ROWS; i++) {
            tableRow = (TableRow) tableLayout.getChildAt(i); // get a row
            for(int j = 1; j <= COLS; j++) {
                Toast.makeText(this, tableRow.getChildCount() + "", Toast.LENGTH_LONG);
                textView = (TextView) tableRow.getChildAt(j); // get a cell in the row
                char ROW = (char) i; // get row char
                int COL = j; // get col int
                if(COL == 1 && (ROW >= 'F' && ROW <= 'J')) {
                    tableRow.removeView(textView);
                    textView.setBackgroundColor(Color.WHITE);
                    tableRow.addView(textView);
                }
            }
            // tableLayout.addView(tableRow);
        }
        return tableLayout;
    }
}
