package com.example.shohin.finalproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NewGame extends AppCompatActivity {

    TextView placeShipsText;
    Button startBattle;

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
    }

    // Start battle on click
    public void startBattle(View view) {
        Intent intent = new Intent(this, Battle.class);
        startActivity(intent);
        finish();
    }
}
