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
    Button previousGame, newGame, settings, exitGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Initialize a title text with an external font
        titleText = (TextView) findViewById(R.id.title);
        Typeface customFont =  Typeface.createFromAsset(getAssets(), "fonts/Top Secret.ttf");
        titleText.setTypeface(customFont);

        // Assign and change the fonts of the buttons
        previousGame = (Button) findViewById(R.id.previousGame);
        newGame = (Button) findViewById(R.id.newGame);
        settings = (Button) findViewById(R.id.settings);
        exitGame = (Button) findViewById(R.id.exitGame);
        previousGame.setTypeface(customFont);
        newGame.setTypeface(customFont);
        settings.setTypeface(customFont);
        exitGame.setTypeface(customFont);
    }

    public void newGame(View view) {
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
    }
}
