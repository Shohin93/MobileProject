package com.example.shohin.finalproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {
    TextView titleText;
    Button newGame,exitGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Initialize a title text with an external font
        titleText = (TextView) findViewById(R.id.title);
        Typeface customFont =  Typeface.createFromAsset(getAssets(), "fonts/Top Secret.ttf");
        titleText.setTypeface(customFont);

        // Assign and change the fonts of the buttons
        newGame = (Button) findViewById(R.id.newGame);
        exitGame = (Button) findViewById(R.id.exitGame);
        newGame.setTypeface(customFont);
        exitGame.setTypeface(customFont);
    }

    public void newGame(View view) {
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
    }
}
