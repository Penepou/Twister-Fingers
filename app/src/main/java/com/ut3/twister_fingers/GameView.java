package com.ut3.twister_fingers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import com.ut3.twister_fingers.Roulette.Roulette;
import com.ut3.twister_fingers.Roulette.RouletteElement;

import com.ut3.twister_fingers.game.Tapis;
import com.ut3.twister_fingers.info.RouletteToolTipText;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;

    private int nbDoigts;
    // declarer les objets du jeu (possiblement des listes)
    RouletteElement test;
    Roulette roulette;
    private RouletteToolTipText rouletteToolTipText;
    private Tapis tapis;

    public GameView(Context context, int nbDoigts) {
        super(context);
        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

        this.nbDoigts = nbDoigts;

        setFocusable(true);

        // instancier les objets du jeu
        roulette = new Roulette(context, nbDoigts);
        tapis = new Tapis(context,nbDoigts, roulette, this);
        roulette.addObs(tapis);
        rouletteToolTipText = new RouletteToolTipText(context);
        roulette.addObs(rouletteToolTipText);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                performClick();
                tapis.onTouchEvent(event);
                return true;
            }
        });
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        try {
            thread.setRunning(false);
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        retry = false;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (canvas != null) {
        }
        // appeler la methode draw des objets du jeu
        roulette.draw(canvas);
        tapis.draw(canvas);
        rouletteToolTipText.draw(canvas);
    }

    public void update() {
    }

    public void stopGame() {
        thread.setRunning(false);
    }
}
