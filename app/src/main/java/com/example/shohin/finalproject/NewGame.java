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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        for(int i = 0; i < 10; i++) {
            tableRow = new TableRow(this);
            for(int j = 0; j < 10; j++) {
                textView = new TextView(this);
                textView.setId(View.generateViewId());
                textView.setBackground(getResources().getDrawable(R.drawable.border));
                textView.setText("A");
                textView.setGravity(Gravity.CENTER);
                textView.setHeight(95);
                textView.setWidth(98);
                tableRow.addView(textView);
            }
            tableLayout.addView(tableRow, new TableLayout.LayoutParams
                    (TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}
