package com.anwesome.ui.horizontalbuttonlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
    private class HorizontalButton {
        private float x,y,w,h,scale = 0;
        private int index;
        public HorizontalButton(float x,float y,float w,float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        public void draw(Canvas canvas,Paint paint) {
            paint.setColor(Color.GRAY);
            canvas.drawRoundRect(new RectF(x,y,x+w,y+h),w/10,w/10,paint);
            paint.setColor(Color.GREEN);
            canvas.save();
            canvas.translate(x+w/2,y+h/2);
            canvas.scale(scale,scale);
            canvas.drawRoundRect(new RectF(-w/2,-h/2,w/2,h/2),w/10,w/10,paint);
            canvas.restore();
        }
        public boolean handleTap(float x,float y) {
            return x>=this.x && x<=this.x+w && y>=this.y && y<=this.y+h;
        }
        public void fill(float factor) {
            scale = factor;
        }
        public void move(float factor) {
            float targetX = w;
            if(index %2 == 0) {
                x = targetX*factor;
            }
        }
    }
}
