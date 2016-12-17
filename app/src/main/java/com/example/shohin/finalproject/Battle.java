package com.example.shohin.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.graphics.Typeface;

import android.view.Gravity;
import android.view.View;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

public class Battle extends AppCompatActivity {

    public final static String MESSAGE = "";

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int WINNING_POINTS = 14;

    private static char[][] userBoard = new char[10][10];
    private static char[][] aiBoard = new char[10][10];
    private static boolean[][] check = new boolean[10][10];
    private static int[] aiShips = {2, 3, 4, 5};

    private boolean userTurn = true;
    private int userHits = 0;
    private int aiHits = 0;

    private Random random = new Random();

    private TableRow tableRow;
    private TableLayout tableLayout;
    private TextView placeBombText, battleTitle, userHitsText, aiHitsText, textView1, textView2;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        // Initialize and populate ships into 2d arrays
        init();
        addAIShips();
        addUserShips();
        print();


        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Top Secret.ttf");
        battleTitle = (TextView) findViewById(R.id.battleTitle);
        userHitsText = (TextView) findViewById(R.id.userHitsText);
        aiHitsText = (TextView) findViewById(R.id.aiHitsText);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        placeBombText = (TextView) findViewById(R.id.placeBomb);

        placeBombText.setTypeface(customFont);
        placeBombText.setTextColor(Color.WHITE);
        placeBombText.setTextSize(30);
        placeBombText.setText("Place your bomb!");

        battleTitle.setTypeface(customFont);
        userHitsText.setTypeface(customFont);
        aiHitsText.setTypeface(customFont);
        textView1.setTypeface(customFont);
        textView2.setTypeface(customFont);

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
                        // If it's user's turn to place a bomb
                        if (userTurn) {
                            String xString = Integer.toString(textView.getId());
                            String yString = Integer.toString(textView.getId());
                            int x, y;
                            String whole = Integer.toString(textView.getId());
                            if(whole.length() == 2) {
                                x = Character.getNumericValue(xString.charAt(0));
                                y = Character.getNumericValue(yString.charAt(1));
                            }
                            else {
                                x = 0;
                                y = Character.getNumericValue(whole.charAt(0));
                            }
                            // If there is HIT by user
                            if (aiBoard[x][y] == 'X') {
                                textView.setText("X");
                                userHits = userHits + 1;
                                userHitsText.setText(userHits + "");
                            } else {
                                textView.setText("O");
                            }
                            userTurn = false;
                            placeBombText.setText("");
                        }
                        if (userHits == WINNING_POINTS) {
                            intent = new Intent(Battle.this, GameOver.class);
                            intent.putExtra(MESSAGE, "Congrats!\nYou have defeated your opponent!");
                            startActivity(intent);
                        }

                        int x = random.nextInt(9);
                        int y = random.nextInt(9);
                        if(check[x][y] == true) {
                            while(check[x][y]) {
                                System.out.println("REDO ON " + x + "  " + y);
                                x = random.nextInt(9);
                                y = random.nextInt(9);
                            }
                        }

                        check[x][y] = true;
                        // If there is HIT by AI
                        if (userBoard[x][y] == 'X') {
                            aiHits = aiHits + 1;
                            aiHitsText.setText(aiHits + "");
                            System.out.println("AI HIT");
                            if (aiHits == WINNING_POINTS) {
                                intent = new Intent(Battle.this, GameOver.class);
                                intent.putExtra(MESSAGE, "You have been defeated!");
                                startActivity(intent);
                            }
                        } else {
                            System.out.println("AI MISSED");
                        }
                        userTurn = true;
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
                userBoard[i][j] = 'O';
            }
        }
    }

    private static void addAIShips() {
        for(int ship: aiShips) {
            boolean added = false;
            while(!added) {
                int x = (int)(aiBoard.length * Math.random());
                int y = (int)(aiBoard[0].length * Math.random());
                boolean vertical = ((int)(10 * Math.random())) % 2 == 0;
                if(vertical) {
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
                        continue;
                    }
                    for(int i = 0; i < ship; i++) {
                        aiBoard[x][y+i] = 'X';
                    }
                    added = true;
                } else {
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

    private static void addUserShips() {
        System.out.println("\n\n\n");
        for(Ship ship: BattleField.ships) {
            int x1 = ship.coordinates.getX1()-1;
            int x2 = ship.coordinates.getX2()-1;
            int y1 = ship.coordinates.getY1()-1;
            int y2 = ship.coordinates.getY2()-1;

            // If the ship is placed vertically
            if(!ship.isShipHorizontal()) {
                while(x1 < x2) {
                    userBoard[x1][y1] = 'X';
                    x1++;
                }
            }

            // If the ship is placed horizontally
            else {
                while(y1 < y2) {
                    userBoard[x1][y1] = 'X';
                    y1++;
                }
            }
        }
    }

    private static void print() {
        for(int i = 0; i < aiBoard.length; i++) {
            for(int j = 0; j < aiBoard[0].length; j++) {
                System.out.print("|"+aiBoard[i][j]);
            }
            System.out.println("");
        }
        System.out.println("______________________________________");
        for(int i = 0; i < userBoard.length; i++) {
            for(int j = 0; j < userBoard[0].length; j++) {
                System.out.print("|"+userBoard[i][j]);
            }
            System.out.println("");
        }
    }
}