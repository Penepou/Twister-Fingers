package com.ut3.twister_fingers.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.ArrayList;

public class Tapis extends SurfaceView {

    ArrayList<Circle> circles = new ArrayList<>();
    public Tapis(Context context) {
        super(context);
        circles.add(new Circle(context,100,100,100));
        circles.add(new Circle(context,300,200,100));
        circles.add(new Circle(context,600,600,100));

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
