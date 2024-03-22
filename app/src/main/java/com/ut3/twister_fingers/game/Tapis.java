package com.ut3.twister_fingers.game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.ArrayList;

public class Tapis extends SurfaceView {

    ArrayList<Circle> circles = new ArrayList<>();
    public Tapis(Context context) {
        super(context);
        int width= Resources.getSystem().getDisplayMetrics().widthPixels;
        int height=Resources.getSystem().getDisplayMetrics().heightPixels;

        for(int i=1; i<=4;i++) {
            for(int j=1; j<=4;j++){
                circles.add(new Circle(context,
                        ((width / 4) * i)-100,
                        ((height/ 4) * j)-100 ,
                        100)
                );
            }

        }

    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (Circle circle : circles) {
            circle.draw(canvas);
        }
    }

    public void update(){

    }
}
