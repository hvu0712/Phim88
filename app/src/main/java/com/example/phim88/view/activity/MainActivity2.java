package com.example.phim88.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.phim88.R;
import com.example.phim88.databinding.ActivityMain2Binding;
import com.example.phim88.databinding.ActivityMainBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController;

public class MainActivity2 extends BaseActivity {
    private ActivityMain2Binding binding;
    private YouTubePlayerTracker tracker;
    private YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        binding = ActivityMain2Binding.inflate(inflater, null, false);
        setContentView(binding.getRoot());
        youTubePlayerView = findViewById(R.id.ytbPlayer);
//        tracker = new YouTubePlayerTracker();
//
//
//        tracker.getState();
//        tracker.getCurrentSecond();
//        tracker.getVideoDuration();
//        tracker.getVideoId();
        IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(0).build();
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.initialize(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
//                youTubePlayer.addListener(tracker);
//                String videoId = "ZlNFpri-Y40";
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    String key = bundle.getString("key");
                    youTubePlayer.cueVideo(key, 0f);
                }
                DefaultPlayerUiController defaultPlayerUiController = new DefaultPlayerUiController(binding.ytbPlayer, youTubePlayer);
                defaultPlayerUiController.showYouTubeButton(false);
                defaultPlayerUiController.showMenuButton(true);
                defaultPlayerUiController.showSeekBar(true);
                defaultPlayerUiController.setFullScreenButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                        Log.e("TAG", "onClick:"+tracker.getCurrentSecond() );
                    }
                });
                binding.ytbPlayer.setCustomPlayerUi(defaultPlayerUiController.getRootView());
//                Log.e("TAG", "onReady: "+tracker.getCurrentSecond());

            }

            @Override
            public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
                super.onStateChange(youTubePlayer, state);
                Log.e("TAG", "onStateChange: "+state);
            }

            @Override
            public void onCurrentSecond(@NonNull YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);
                Log.e("TAG", "onCurrentSecond: "+second);
            }
        }, options);
    }

}