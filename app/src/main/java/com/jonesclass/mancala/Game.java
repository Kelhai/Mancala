package com.example.mancala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Game extends AppCompatActivity {

    Button[][] buttons;

    TextView otherTextView, playerTextView, turnTextView;

    boolean playerTurn = true;
    int[][] values = {
            {
                4, 4,
                4, 4,
                4, 4
            },
            {
                4, 4,
                4, 4,
                4, 4
            },
            {
                0, 0
            }
    };

    Random randObj = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        otherTextView = findViewById(R.id.other_textView);
        playerTextView = findViewById(R.id.player_textView);
        turnTextView = findViewById(R.id.turn_textView);

        buttons = new Button[][]{
                {
                        findViewById(R.id.button00), findViewById(R.id.button01),
                        findViewById(R.id.button02), findViewById(R.id.button03),
                        findViewById(R.id.button04), findViewById(R.id.button05)
                },
                {
                        findViewById(R.id.button10), findViewById(R.id.button11),
                        findViewById(R.id.button12), findViewById(R.id.button13),
                        findViewById(R.id.button14), findViewById(R.id.button15)
                }
        };

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonClicked(finalI * 10 + finalJ);
                    }
                });
            }
        }
    }

    protected void buttonClicked(int num) {
        int value = values[num / 10][num % 10];
        if ((playerTurn && num / 10 == 1) || (!playerTurn && num / 10 == 0)) {
            return;
        }
        if (value != 0) {
            values[num / 10][num % 10] = 0;
            move(num, value);
        }
        int win = checkWin();
        if (win != -1) {
            for (Button[] buttonArr : buttons) {
                for (Button button : buttonArr) {
                    button.setEnabled(false);
                }
            }
            turnTextView.setText(win == 0 ? "You Win!!" : "You Lose :(");
            return;
        }
        // ai
        while (!playerTurn) {
            int r, rVal;
            do {
                r = randObj.nextInt(6);
                rVal = values[1][r];
            } while (rVal == 0);
            values[1][r] = 0;
            move(10 + r, rVal);

            win = checkWin();
            if (win != -1) {
                for (Button[] buttonArr : buttons) {
                    for (Button button : buttonArr) {
                        button.setEnabled(false);
                    }
                }
                turnTextView.setText(win == 0 ? "You Win!!" : "You Lose :(");
                return;
            }
        }
        //
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setText(String.valueOf(values[i][j]));
            }
        }
        playerTextView.setText(String.valueOf(values[2][0]));
        otherTextView.setText(String.valueOf(values[2][1]));
        turnTextView.setText(playerTurn ? "Your Turn" : "Opponent's Turn");
    }

    protected void move(int num, int value) { // animation goes somewhere in this function Andrew
        if (value == 0) {
            if (num == 20 || num == 21) {
                return;
            }
            if (values[playerTurn ? 1 : 0][num % 10] != 0
                    && values[playerTurn ? 0 : 1][num % 10] == 1) {
                // captures
                int total = values[1][num % 10] + values[0][num % 10];
                values[1][num % 10] = 0; values[0][num % 10] = 0;
                values[2][playerTurn ? 0 : 1] += total;
            }
            playerTurn = !playerTurn;
            return;
        }

        int newVal;
        if (num == 20) { // player side pot
            newVal = 15;
        } else if (num == 21) { // opponent side pot
            newVal = 00;
        } else if (num / 10 == 0) { // player side
            if (num % 10 < 5) { // not changing
                newVal = num + 1;
            } else { // changing
                newVal = playerTurn ? 20 : 15;
            }
        } else { // opponent side
            if (num % 10 > 0) { // not changing
                newVal = num - 1;
            } else { // changing
                newVal = playerTurn ? 00 : 21;
            }
        }
        values[newVal / 10][newVal % 10]++;
        move(newVal, value - 1);
    }

    int checkWin() {
        int total1 = 0, total2 = 0;
        for (int i = 0; i < values[0].length; i++) {
            total1 += values[0][i];
            total2 += values[1][i];
        }
        if (total1 == 0 || total2 == 0) {
            return (total1 + values[2][0] > total2 + values[2][1]) ? 0 : 1;
        }
        return -1;
    }
}