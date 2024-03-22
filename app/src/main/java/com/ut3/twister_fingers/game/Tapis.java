package com.ut3.twister_fingers.game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.ut3.twister_fingers.Roulette.SpotColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tapis extends SurfaceView {

    ArrayList<Circle> circles = new ArrayList<>();
    public Tapis(Context context) {
        super(context);
        int width= Resources.getSystem().getDisplayMetrics().widthPixels;
        int height=Resources.getSystem().getDisplayMetrics().heightPixels;
        SpotColor[] spotColorValues = SpotColor.values();
        List<SpotColor> spotColorArrayList = Arrays.asList(spotColorValues);

        for(int i=1; i<=4;i++) {
            for(int j=1; j<=6;j++){
                circles.add(new Circle(context,
                        ((width / 4) * i)-100,
                        ((height/ 6) * j)-100 ,
                        100 ,spotColorArrayList.get(i-1).getColor())
                );
            }
        }
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

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
