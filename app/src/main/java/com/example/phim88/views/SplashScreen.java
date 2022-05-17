package com.example.phim88.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.phim88.R;
import com.example.phim88.view.activity.MainActivity;

public class SplashScreen extends AppCompatActivity {

    private static int FLASH_TIME_OUT = 3000;

    View first , second ,third , fourth , fifth,sixth;
    TextView h , slogan;

    Animation topAnimation , middleAnimation ,bottonAnimation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);
        bottonAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        first = findViewById(R.id.line1);
        second = findViewById(R.id.line2);
        third = findViewById(R.id.line3);
        fourth = findViewById(R.id.line4);
        fifth = findViewById(R.id.line5);
        sixth = findViewById(R.id.line6);

        h = findViewById(R.id.textH);
        slogan= findViewById(R.id.tagLine);

        first.setAnimation(topAnimation);
        second.setAnimation(topAnimation);
        third.setAnimation(topAnimation);
        fourth.setAnimation(topAnimation);
        fifth.setAnimation(topAnimation);
        sixth.setAnimation(topAnimation);

        h.setAnimation(middleAnimation);
        slogan.setAnimation(middleAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },FLASH_TIME_OUT);
       
    }
}