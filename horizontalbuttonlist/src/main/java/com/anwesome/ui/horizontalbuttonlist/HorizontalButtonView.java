package com.anwesome.ui.horizontalbuttonlist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 20/05/17.
 */
public class HorizontalButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    private HorizontalButton tappedButton = null;
    private AnimationHandler animationHandler = new AnimationHandler();
    private List<HorizontalButton> horizontalButtons = new ArrayList<>();
    public HorizontalButtonView(Context context) {
        super(context);
    }
    public void addHorizontalButton(com.anwesome.ui.horizontalbuttonlist.OnClickListener onClickListener) {
        if(time == 0) {
            horizontalButtons.add(new HorizontalButton(onClickListener));
        }
    }
    public void removeOtherButton(float factor) {
        if(tappedButton != null) {
            tappedButton.fill(factor);
            int i = 0;
            for (HorizontalButton horizontalButton:horizontalButtons) {
                if(horizontalButton != tappedButton) {
                    horizontalButton.move(factor);
                    horizontalButton.setIndex(i);
                    i++;
                }
            }
            postInvalidate();
        }
    }
    public void moveTappedButton(float factor) {
        if(tappedButton != null) {
            tappedButton.move(factor);
            postInvalidate();
        }
    }
    public void onDraw(Canvas canvas) {
        if(time == 0 && horizontalButtons.size()>0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            int width = 4*w/5,gap = h/(2*horizontalButtons.size()+1),i=0;
            float y = gap;
            for(HorizontalButton horizontalButton:horizontalButtons) {
                horizontalButton.setDimension(w/2-width/2,y,width,gap);
                horizontalButton.setIndex(i);
                y += 2*gap;
                i++;
            }
        }
        for(HorizontalButton horizontalButton:horizontalButtons) {
            horizontalButton.draw(canvas,paint);
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            for (HorizontalButton horizontalButton : horizontalButtons) {
                if (horizontalButton.handleTap(event.getX(), event.getY())) {
                    tappedButton = horizontalButton;
                    break;
                }
            }
            if (tappedButton != null) {
                animationHandler.start();
            }
        }
        return true;
    }
    private class HorizontalButton {
        private float x,y,w,h,scale = 0;
        private com.anwesome.ui.horizontalbuttonlist.OnClickListener onClickListener;
        private int index;
        public HorizontalButton(com.anwesome.ui.horizontalbuttonlist.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }
        public void setDimension(float x,float y,float w,float h) {
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
            float targetX = w+w/4;
            if(index %2 == 1) {
                targetX*=-1;
            }
            x = targetX*factor;
        }
        public int hashCode() {
            return (int)(x+y+w+h+scale);
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator animTracker = ValueAnimator.ofFloat(0,1);
        private int animFlag = 0;
        private boolean isAnimating = false;
        public AnimationHandler() {
            animTracker.setDuration(500);
            animTracker.addUpdateListener(this);
            animTracker.addListener(this);
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if(isAnimating) {
                if (animFlag == 0) {
                    removeOtherButton((float)valueAnimator.getAnimatedValue());
                } else {
                    moveTappedButton((float)valueAnimator.getAnimatedValue());
                }
            }
        }
        public void onAnimationEnd(Animator animator) {

            if(isAnimating) {
                animFlag = animFlag == 0 ? 1 : 0;
                isAnimating = false;
                if (animFlag == 1) {
                    start();
                    if(tappedButton != null && tappedButton.onClickListener!=null) {
                        tappedButton.onClickListener.onClick();
                    }

                }
                else {
                    tappedButton = null;
                }

            }
        }
        public void start() {
            if(!isAnimating) {
                animTracker.start();
                isAnimating = true;
            }
        }
    }
}
