package com.jonesclass.mancala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Game extends AppCompatActivity {

    Button[][] buttons = {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}