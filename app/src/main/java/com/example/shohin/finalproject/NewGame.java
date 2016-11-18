package com.example.shohin.finalproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class NewGame extends AppCompatActivity {
    TableLayout tableLayout;
    TableRow tableRow;
    TextView textView;

    char asciiChar = 65;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        for(int i = 1; i <= 11; i++) {
            tableRow = new TableRow(this);
            for(int j = 1; j <= 11; j++) {
                textView = new TextView(this);
                textView.setId(View.generateViewId());
                textView.setBackground(getResources().getDrawable(R.drawable.border));
                if(i == 1 && j != 1) {
                    textView.setText(Integer.toString(j - 1));
                }
                if(i != 1 && j == 1) {
                    textView.setText(Character.toString(asciiChar++));
                }
                textView.setGravity(Gravity.CENTER);
                textView.setHeight(85);
                textView.setWidth(89);
                tableRow.addView(textView);

            }
            tableLayout.addView(tableRow, new TableLayout.LayoutParams
                    (TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}
