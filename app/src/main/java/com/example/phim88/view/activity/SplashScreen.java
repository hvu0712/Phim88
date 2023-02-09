package com.example.phim88.view.activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.phim88.R;
import com.google.android.youtube.player.YouTubeBaseActivity;

public class SplashScreen extends YouTubeBaseActivity {
    private static final int FLASH_TIME_OUT = 3000;

    private View first, second, third, fourth, fifth, sixth;
    private TextView h, slogan;
    private static final String TAG = "SplashScreen";

    private Animation topAnimation, middleAnimation, bottonAnimation;
    private ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback(){
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            Log.e(TAG, "onAvailable: ");
        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            Log.e(TAG, "onLost: ");
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            final boolean unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
            final boolean unmetered1 = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            Log.e(TAG, "onCapabilitiesChanged: "+unmetered+ " "+unmetered1);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(ConnectivityManager.class);
        connectivityManager.requestNetwork(networkRequest, networkCallback);


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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
        slogan = findViewById(R.id.tagLine);

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
        }, FLASH_TIME_OUT);

    }
}