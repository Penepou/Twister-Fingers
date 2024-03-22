package com.ut3.twister_fingers.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Circle extends View {
    private int x, y;
    private int radius;
    private Paint paint;

    private int baseColor;

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
    public boolean contains(float xTouch, float yTouch) {
        double distance = Math.sqrt(Math.pow(xTouch - x, 2) + Math.pow(yTouch - y, 2));
        return distance <= radius;
    }

    public Circle(Context context, int x, int y, int radius, int color) {
        super(context);
        this.baseColor = color;
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    public void update() {
    }

    public void onTouchDown() {
        paint.setColor(baseColor + 100);
    }

    public void onTouchUp() {
        paint.setColor(baseColor);

    }


    public int getColor(){
        return paint.getColor();
    }
}

