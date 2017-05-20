package com.anwesome.ui.horizontalbuttonlist;

import android.app.Activity;
import android.content.pm.ActivityInfo;

/**
 * Created by anweshmishra on 20/05/17.
 */
public class HorizontalButtonList {
    private Activity activity;
    private HorizontalButtonView horizontalButtonView;
    private boolean isShown = false;
    public HorizontalButtonList(Activity activity) {
        this.activity = activity;
        horizontalButtonView = new HorizontalButtonView(activity);
    }
    public void addButton(OnClickListener onClickListener) {
        if(!isShown) {
            horizontalButtonView.addHorizontalButton(onClickListener);
        }
    }
    public void show() {
        if(!isShown) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            activity.setContentView(horizontalButtonView);
            isShown = true;
        }
    }
}
