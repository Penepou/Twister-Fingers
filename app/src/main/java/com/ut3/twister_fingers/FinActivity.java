package com.ut3.twister_fingers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinActivity extends AppCompatActivity {
    private Button menu;
    private TextView score;
    private int scoreI;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fin);

        menu = findViewById(R.id.menu);
        score = findViewById(R.id.score);
        scoreI = getIntent().getIntExtra("score", -1);
        score.setText(score.getText()+ String.valueOf(scoreI));

        menu.setOnClickListener(v -> {
            Intent intentMain = new Intent(this, MainActivity.class);
            intentMain.putExtra("score", scoreI);
            this.startActivity(intentMain);
        });
    }
}