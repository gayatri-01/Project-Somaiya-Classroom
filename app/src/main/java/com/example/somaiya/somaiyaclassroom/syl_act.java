package com.example.somaiya.somaiyaclassroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import android.widget.TextView;

import static android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class syl_act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syl_act);
        Magnify mag = new Magnify();

        float zoomFactor = 1.25f;
        if (Magnify.getInstance().getData())
            mag.enlarge(true, findViewById(android.R.id.content), zoomFactor);


    }
}

