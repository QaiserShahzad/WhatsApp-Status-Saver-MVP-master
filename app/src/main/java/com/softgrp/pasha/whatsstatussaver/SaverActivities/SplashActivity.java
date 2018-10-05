package com.softgrp.pasha.whatsstatussaver.SaverActivities;

import HelperDir.SharedPref;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.softgrp.pasha.whatsstatussaver.R;

/**
 * Created by emmanuel on 27/09/2017.
 */

public class SplashActivity extends AppCompatActivity{

    int a;
    LinearLayout up,down;
    Animation uptodown , downtoup;
    private static final long DELAY = 6000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        writeIntoSharedPref(getApplicationContext());

        up=(LinearLayout)findViewById(R.id.UP);
        uptodown= AnimationUtils.loadAnimation(this, R.anim.uptodown);
        up.setAnimation(uptodown);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, DELAY);
    }

    private void writeIntoSharedPref(Context applicationContext) {
        SharedPref.writeIntoSharedPref(applicationContext, "Entered");
    }

}
