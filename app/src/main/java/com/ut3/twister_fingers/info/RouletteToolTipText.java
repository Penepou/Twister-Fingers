package com.ut3.twister_fingers.info;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.ut3.twister_fingers.Roulette.RouletteElement;
import com.ut3.twister_fingers.util.RouletteObservateur;

public class RouletteToolTipText extends SurfaceView implements RouletteObservateur {
    private String rouletteToolTip = "";
    private final Paint paint = new Paint();
    public RouletteToolTipText(Context context) {
        super(context);

        paint.setTextSize(30);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawText(rouletteToolTip, 20 , 180, paint);
    }

    @Override
    public void notify(RouletteElement element) {
        rouletteToolTip = element.getInfo();
    }
}
