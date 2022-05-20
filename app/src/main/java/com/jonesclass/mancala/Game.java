package com.jonesclass.mancala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
            }
    };

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

    }

    protected void move(int num, int value) {
        //recurse
    }
}