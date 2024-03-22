package com.ut3.twister_fingers.Roulette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;

public class RouletteElement extends SurfaceView implements SurfaceHolder.Callback{

    private final SpotColor color;
    private final Hand hand;
    private final Fingers finger;
    private float x;
    private float y;

    public RouletteElement(Context context, SpotColor givenColor, Hand givenHand, Fingers givenFinger, float givenX, float givenY) {
        super(context);
        this.color = givenColor;
        this.hand = givenHand;
        this.finger = givenFinger;
        this.x = givenX;
        this.y = givenY;

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

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            Paint paint = new Paint();
            paint.setColor(color.getColor());
            canvas.drawRect(x, y, x+ 100, y + 100, paint);

            Paint textPaint = new Paint();
            textPaint.setColor(Color.BLACK);
            canvas.drawText(String.valueOf(finger), x + 30, y + 30, textPaint);

            canvas.drawText(String.valueOf(hand), x + 30, y + 30 + 60, textPaint);
        }
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        invalidate();
    }

    public void update() {
        this.x = x-150;
        invalidate();
    }

    public int getColor(){
        return color.getColor();
    }
}
