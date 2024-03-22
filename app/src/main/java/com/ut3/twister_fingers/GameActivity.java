package com.ut3.twister_fingers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        View tapis = findViewById(R.id.tapis);

        GameView gameView = new GameView(this, getIntent().getIntExtra("nbDoigts", 3));

        ViewGroup parent = (ViewGroup) tapis.getParent();

        int index = parent.indexOfChild(tapis);
        parent.removeView(tapis);

        FrameLayout container = new FrameLayout(this);
        container.setLayoutParams(tapis.getLayoutParams());

        container.addView(tapis);
        container.addView(gameView);

        parent.addView(container, index);

    }
}