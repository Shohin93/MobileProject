package com.example.shohin.finalproject;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Battle extends AppCompatActivity {

    private TextView placeBombText;

    private static final int ROWS = 10;
    private static final int COLS = 10;

    private static char[][] userBoard = new char[10][10];
    private static char[][] aiBoard = new char[10][10];
    private static int[] aiShips = {2, 3, 4, 5};
    private static int totalUserHits;
    private static int totalAIHits;

    private boolean userTurn;
    private int userHits;
    private int aiHits;

    TableRow tableRow;
    TableLayout tableLayout;
    TextView battleTitle, userHitsText, aiHitsText, textView1, textView2;

    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        // Initialize and populate ships into 2d arrays
        init();
        addAIShips();
        addUserShips();
        print();
        // Log.d("this is my array", "arr: \n\n" + Arrays.toString(aiBoard));

        userTurn = true;
        userHits = 0;
        aiHits = 0;
        totalUserHits = 14;
        totalAIHits = 14;

        random = new Random();

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

                if(userTurn) {
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // If it's user's turn to place a bomb
                            if (userTurn) {
                                placeBombText.setText("Place your bomb!");
                                String xString = Integer.toString(textView.getId());
                                String yString = Integer.toString(textView.getId());
                                int x = Character.getNumericValue(xString.charAt(0));
                                int y = Character.getNumericValue(yString.charAt(1));

                                // If there is HIT by user
                                if (aiBoard[x][y] == 'X') {
                                    textView.setText("X");
                                    userHitsText.setText(++userHits + "");
                                    totalUserHits--;
                                    if (totalUserHits == 0) {
                                        Toast.makeText(Battle.this, "You won!", Toast.LENGTH_LONG);
                                    }
                                } else {
                                    textView.setText("O");
                                }
                                userTurn = false;
                                placeBombText.setText("");
                            }
                        }
                    });
                } else {
                    placeBombText.setText("AI places bomb!");
                    int x = random.nextInt(9);
                    int y = random.nextInt(9);
                    // If there is HIT by AI
                    if(userBoard[x][y] == 'X') {
                        textView.setText("X");
                        aiHitsText.setText(++aiHits + "");
                        totalAIHits--;
                        if(totalAIHits == 0) {
                            Toast.makeText(Battle.this, "You lost!", Toast.LENGTH_LONG);
                        }
                    } else {
                        textView.setText("O");
                    }
                    userTurn = true;
                    placeBombText.setText("Place your bomb!");
                }
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

    private static void addUserShips() {
        System.out.println("\n\n\n");
        for(Ship ship: BattleField.ships) {
            int x1 = ship.coordinates.getX1()-1;
            int x2 = ship.coordinates.getX2()-1;
            int y1 = ship.coordinates.getY1()-1;
            int y2 = ship.coordinates.getY2()-1;
            //Toast.makeText(Battle.this, x1 + "", Toast.LENGTH_LONG).show();
            System.out.println("x1 and x2 are " + x1 + ", " + x2);

            // If the ship is placed vertically
            if(!ship.isShipHorizontal()) {
                while(x1 < x2) {
                    userBoard[x1][y1] = 'X';
                    x1++;
                }
            }

            // If the ship is placed horizontally
            else {
                // NEEDS IMPLEMENTATION!!!!!!!!
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
        System.out.println("\n\n");
        for(int i = 0; i < userBoard.length; i++) {
            for(int j = 0; j < userBoard[0].length; j++) {
                System.out.print(""+userBoard[i][j]);
            }
            System.out.println("");
        }
    }
}
