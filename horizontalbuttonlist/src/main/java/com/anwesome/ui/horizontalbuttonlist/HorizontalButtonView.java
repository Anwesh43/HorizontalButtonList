package com.anwesome.ui.horizontalbuttonlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 20/05/17.
 */
public class HorizontalButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public HorizontalButtonView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {

    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

}
