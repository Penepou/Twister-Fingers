package com.ut3.twister_fingers;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;

    private int nbDoigts;
    // declarer les objets du jeu (possiblement des listes)

    public GameView(Context context, int nbDoigts) {
        super(context);
        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

        this.nbDoigts = nbDoigts;

        setFocusable(true);

        // instancier les objets du jeu
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

        if (canvas != null) {
            /*
            canvas.drawColor(Color.RED);
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 0));
            canvas.drawRect(100, 100, 100, 200, paint);*/
        }
    }

    public void update() {
    }
}
