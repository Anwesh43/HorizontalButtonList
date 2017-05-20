package com.anwesome.ui.horizontalbuttonlistdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.horizontalbuttonlist.HorizontalButtonView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HorizontalButtonView horizontalButtonView = new HorizontalButtonView(this);
        for(int i=0;i<7;i++) {
            horizontalButtonView.addHorizontalButton();
        }
        setContentView(horizontalButtonView);
    }
}
