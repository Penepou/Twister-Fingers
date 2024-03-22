package com.ut3.twister_fingers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button easy;
    private Button moyen;
    private Button difficile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        easy = findViewById(R.id.facile);
        moyen = findViewById(R.id.moyen);
        difficile = findViewById(R.id.difficile);

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