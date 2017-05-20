package com.anwesome.ui.horizontalbuttonlistdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.anwesome.ui.horizontalbuttonlist.HorizontalButtonList;
import com.anwesome.ui.horizontalbuttonlist.HorizontalButtonView;
import com.anwesome.ui.horizontalbuttonlist.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HorizontalButtonList horizontalButtonList = new HorizontalButtonList(this);
        for(int i=0;i<7;i++) {
            final int index = i+1;
            horizontalButtonList.addButton(new OnClickListener(){
                public void onClick() {
                    Toast.makeText(MainActivity.this, index+" clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
        horizontalButtonList.show();
    }
}
