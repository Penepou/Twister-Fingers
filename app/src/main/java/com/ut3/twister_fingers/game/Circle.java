package com.ut3.twister_fingers.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class Circle extends View {
    private int x, y;
    private int radius;
    private Paint paint;

    public Circle(Context context, int x, int y, int radius, int color) {
        super(context);
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    public void update() {
    }
}

