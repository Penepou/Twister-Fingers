package com.ut3.twister_fingers.Roulette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Roulette extends SurfaceView implements SurfaceHolder.Callback {
    List<RouletteElement> elements = new ArrayList<>();
    float elementWidth = 100;


    public Roulette(Context context) {
        super(context);
        getHolder().addCallback(this);

        instanciateElements();


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, 200);


    }

    private void instanciateElements() {
        for (SpotColor color : SpotColor.values()){
            for (Fingers fingers : Fingers.values()){
                for (Hand hand : Hand.values()){
                    elements.add(new RouletteElement(this.getContext(), color, hand, fingers, 0, 0));
                }
            }
        }
        Collections.shuffle(elements);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
        // Draw each RouletteElement on the canvas
        for (int i = 0;  i < elements.size(); i++) {
            elements.get(i).setPosition(elementWidth*i,0);
            elements.get(i).draw(canvas);
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    public void update() {
        Collections.rotate(elements, -1);
        for (RouletteElement elements : elements){
            elements.update();
        }
    }
}
