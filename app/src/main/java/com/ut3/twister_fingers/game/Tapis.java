package com.ut3.twister_fingers.game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.ut3.twister_fingers.FinActivity;
import com.ut3.twister_fingers.Roulette.SpotColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tapis extends View {
    ArrayList<Circle> circles = new ArrayList<>();
    Context context;

    private int circleRadius = 100;

    public Tapis(Context context) {
        super(context);
        this.context = context;
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        SpotColor[] spotColorValues = SpotColor.values();
        List<SpotColor> spotColorArrayList = Arrays.asList(spotColorValues);




        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 5; j++) {
                circles.add(new Circle(context,
                        ((width / 4) * i) - circleRadius,
                        ((height / 6) * j) - circleRadius + 200,
                        circleRadius, spotColorArrayList.get(i - 1).getColor())
                );
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        int pointerCount = event.getPointerCount();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if(pointerCount > 4){
                    Intent intentFin = new Intent(context, FinActivity.class);
                    context.startActivity(intentFin);
                }
                // Nouveau toucher détecté
                Log.d("Tapis", "Nouveau toucher détecté, nombre de touchers simultanés : " + pointerCount);
                break;
            case MotionEvent.ACTION_MOVE:
                // Mouvement du toucher
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                // Un toucher a été relâché
                Log.d("Tapis", "Un toucher a été relâché, nombre de touchers simultanés : " + pointerCount);
                break;
        }        return true;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (Circle circle : circles) {
            circle.draw(canvas);
        }
    }

    public void update() {
    }
}
