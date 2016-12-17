package com.example.shohin.finalproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    private Button mainMenuButton, exitGameButton;
    private TextView gameOverTitle, result;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // Initialize the view stuff
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Top Secret.ttf");
        mainMenuButton = (Button) findViewById(R.id.mainMenu);
        exitGameButton = (Button) findViewById(R.id.exit);
        gameOverTitle = (TextView) findViewById(R.id.gameOverTitle);
        result = (TextView) findViewById(R.id.result);

        mainMenuButton.setTypeface(customFont);
        exitGameButton.setTypeface(customFont);
        gameOverTitle.setTypeface(customFont);
        result.setTypeface(customFont);

        intent = getIntent();
        String message = intent.getStringExtra(Battle.MESSAGE);
        result.setText(message);
    }

    public void mainMenu(View view) {
        intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    public void exit(View view) {
        finish();
        System.exit(0);
    }
}
