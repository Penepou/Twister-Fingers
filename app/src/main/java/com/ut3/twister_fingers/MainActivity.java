package com.ut3.twister_fingers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button easy;
    private Button moyen;
    private Button difficile;
    private TextView meilleurScoreView;
    private int score;

    private int meilleurScore = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        easy = findViewById(R.id.facile);
        moyen = findViewById(R.id.moyen);
        difficile = findViewById(R.id.difficile);
        meilleurScoreView = findViewById(R.id.meilleur_score);
        score = getIntent().getIntExtra("score", -1);

        if(score==-1){
            meilleurScoreView.setVisibility(View.INVISIBLE);
        }else{
            meilleurScoreView.setVisibility(View.VISIBLE);
            if(meilleurScore < score){
                meilleurScore = score;
            }
            meilleurScoreView.setText(meilleurScoreView.getText() + String.valueOf(score));
        }

        easy.setOnClickListener(v -> {
            Context context = v.getContext();
            play(3, context);
        });

        moyen.setOnClickListener(v -> {
            Context context = v.getContext();
            play(4, context);
        });

        difficile.setOnClickListener(v -> {
            Context context = v.getContext();
            play(5, context);
        });
    }

    public void play(int nbDoigts, Context context){
        Intent intentMain = new Intent(this, GameActivity.class);
        intentMain.putExtra("nbDoigts", nbDoigts);
        this.startActivity(intentMain);
    }
}