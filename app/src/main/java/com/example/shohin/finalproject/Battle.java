package com.example.shohin.finalproject;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Battle extends AppCompatActivity {

    private static final int ROWS = 10;
    private static final int COLS = 10;

    TableRow tableRow;
    TableLayout tableLayout;
    TextView battleTitle, myHits, aiHits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

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

                        Toast.makeText(Battle.this, "FiveCellShip position is " + BattleField.ships[0].coordinates.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            tableLayout.addView(tableRow, new TableLayout.LayoutParams
                    (TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}
