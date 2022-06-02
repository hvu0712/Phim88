package com.example.phim88;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.phim88.databinding.ActivityYoutubeBinding;
import com.example.phim88.util.Const;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import java.util.List;

public class Youtube extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    ActivityYoutubeBinding binding;
    YouTubePlayer mYouTubePlayer = new YouTubePlayer() {
        @Override
        public void release() {

        }

        @Override
        public void cueVideo(String s) {

        }

        @Override
        public void cueVideo(String s, int i) {

        }

        @Override
        public void loadVideo(String s) {

        }

        @Override
        public void loadVideo(String s, int i) {

        }

        @Override
        public void cuePlaylist(String s) {

        }

        @Override
        public void cuePlaylist(String s, int i, int i1) {

        }

        @Override
        public void loadPlaylist(String s) {

        }

        @Override
        public void loadPlaylist(String s, int i, int i1) {

        }

        @Override
        public void cueVideos(List<String> list) {

        }

        @Override
        public void cueVideos(List<String> list, int i, int i1) {

        }

        @Override
        public void loadVideos(List<String> list) {

        }

        @Override
        public void loadVideos(List<String> list, int i, int i1) {

        }

        @Override
        public void play() {

        }

        @Override
        public void pause() {

        }

        @Override
        public boolean isPlaying() {
            return false;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public void next() {

        }

        @Override
        public void previous() {

        }

        @Override
        public int getCurrentTimeMillis() {
            return 0;
        }

        @Override
        public int getDurationMillis() {
            return 0;
        }

        @Override
        public void seekToMillis(int i) {

        }

        @Override
        public void seekRelativeMillis(int i) {

        }

        @Override
        public void setFullscreen(boolean b) {

        }

        @Override
        public void setOnFullscreenListener(OnFullscreenListener onFullscreenListener) {

        }

        @Override
        public int getFullscreenControlFlags() {
            return 0;
        }

        @Override
        public void setFullscreenControlFlags(int i) {

        }

        @Override
        public void addFullscreenControlFlag(int i) {

        }

        @Override
        public void setPlayerStyle(PlayerStyle playerStyle) {

        }

        @Override
        public void setShowFullscreenButton(boolean b) {

        }

        @Override
        public void setManageAudioFocus(boolean b) {

        }

        @Override
        public void setPlaylistEventListener(PlaylistEventListener playlistEventListener) {

        }

        @Override
        public void setPlayerStateChangeListener(PlayerStateChangeListener playerStateChangeListener) {

        }

        @Override
        public void setPlaybackEventListener(PlaybackEventListener playbackEventListener) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYoutubeBinding.inflate(getLayoutInflater(), null, false);

        binding.ytb.initialize(Const.info.utubeKey, this);

        setContentView(binding.getRoot());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        mYouTubePlayer = youTubePlayer;
        Intent intent = getIntent();
        String m = intent.getStringExtra("trailer");
        youTubePlayer.loadVideo(m, 0);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.e("TAG", "onInitializationFailure: " + youTubeInitializationResult);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}