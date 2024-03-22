package com.ut3.twister_fingers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.ut3.twister_fingers.game.Circle;
import com.ut3.twister_fingers.game.Tapis;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;

    // declarer les objets du jeu (possiblement des listes)
    private Tapis tapis;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

        setFocusable(true);

        // instancier les objets du jeu
        tapis = new Tapis(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        retry = false;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // appeler la methode draw des objets du jeu
        tapis.draw(canvas);
    }

    public void update() {
    }
}
