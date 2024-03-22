package com.ut3.twister_fingers.game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.ut3.twister_fingers.FinActivity;
import com.ut3.twister_fingers.Roulette.Roulette;
import com.ut3.twister_fingers.Roulette.RouletteElement;
import com.ut3.twister_fingers.Roulette.SpotColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tapis extends View {
    ArrayList<Circle> circles = new ArrayList<>();
    Context context;
    ArrayList<Integer> listCorrectTouch;
    int nbDoigt;
    Roulette roulette;
    Vibrator vibrator;
    int nbLifes = 3;

    boolean aPoseUnMauvaisDoigt = false;
    RouletteElement resultatCourant = null;
    private int circleRadius = 100;

    public Tapis(Context context, int nbdoigt, Roulette roulette) {
        super(context);
        this.context = context;
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        SpotColor[] spotColorValues = SpotColor.values();
        List<SpotColor> spotColorArrayList = Arrays.asList(spotColorValues);

        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 5; j++) {
                circles.add(new Circle(context,
                        ((width / 4) * i) - circleRadius,
                        ((height / 6) * j) - circleRadius + 200,
                        circleRadius, spotColorArrayList.get(i - 1).getColor())
                );
            }
        }
        //initialisation de la liste des cercle appuyé, initialisé à 0
        listCorrectTouch = new ArrayList<Integer>(Collections.nCopies(circles.size(), 0));
        lancerRoulette();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        int pointerCount = event.getPointerCount();

        switch (action) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:  // Nouveau toucher détecté
                if(resultatCourant == null){
                    loseLife();
                    aPoseUnMauvaisDoigt = true;
                    break;
                }
                for (int i = 0; i < pointerCount; i++) {
                    float x = event.getX(i);
                    float y = event.getY(i);
                    updateListWhenPressed(x,y);
                }

                Log.d("Tapis", "Nouveau toucher détecté, nombre de touchers simultanés : " + pointerCount);
                resultatCourant = null;
                break;

            case MotionEvent.ACTION_MOVE:
                // Mouvement du toucher
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                // Un toucher a été relâché
                for (int i = 0; i < pointerCount; i++) {
                    float x = event.getX(i);
                    float y = event.getY(i);
                    for (Circle circle : circles) {
                        if (circle.contains(x, y)) {
                            circle.onTouchUp();
                            break;
                        }
                    }
                }

                if(aPoseUnMauvaisDoigt){
                    aPoseUnMauvaisDoigt = false;
                }
                else{
                    loseLife();
                }
                Log.d("Tapis", "Un toucher a été relâché, nombre de touchers simultanés : " + pointerCount);
                break;
        }
        return true;
    }

    public boolean updateListWhenPressed(float x, float y){

        for (Circle circle : circles) {
            if (circle.contains(x, y)) {
                circle.onTouchDown();
                int index = circles.indexOf(circle);
                listCorrectTouch.set(index,1);
                Log.d("Tapis", "Cercle touché : " + listCorrectTouch.toString());
                if(!isCorrect(circle)){
                    aPoseUnMauvaisDoigt = true;
                    loseLife();
                }
                else{
                    lancerRoulette();
                    break;
                }
                break;
            }
        }
        return false;
    }

    public boolean isCorrect(Circle circle){
        return circle.getColor()==resultatCourant.getColor();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (Circle circle : circles) {
            circle.draw(canvas);
        }
        roulette.draw(canvas);
    }

    public void update() {
    }

    public void lancerRoulette(){
    }

    public void loseLife(){
        vibrator.vibrate(200);
        nbLifes = nbLifes - 1;
        if( nbLifes == 0){
            Intent intentFin = new Intent(context, FinActivity.class);
            context.startActivity(intentFin);
        }
    }
}
