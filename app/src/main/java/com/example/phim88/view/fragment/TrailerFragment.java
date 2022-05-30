package com.example.phim88.view.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentTrailerBinding;
import com.example.phim88.model.Video.Video;
import com.example.phim88.util.Const;
import com.example.phim88.viewmodel.VideoViewModel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;

import java.util.ArrayList;
import java.util.List;

public class TrailerFragment extends BaseFragment {

    public int data;
    String a;
    private FragmentTrailerBinding binding;
    private List<Video> listVideo = new ArrayList<>();
    private VideoViewModel videoViewModel;
    private static final String TAG = "TrailerFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trailer, container, false);
        getLifecycle().addObserver(binding.vvTrailer);
        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        videoViewModel.getListVideo().observe(getViewLifecycleOwner(), videos -> {
            if (videos.size() > 0 && videos != null) {
                for (Video video : videos) {
                    if (video.getName().equals("Official Trailer") == true) {
                        listVideo.add(new Video(video.getKey()));

                            binding.vvTrailer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                                @Override
                                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                    super.onReady(youTubePlayer);
                                    youTubePlayer.cueVideo(video.getKey(), 0);
                                    YouTubePlayerTracker tracker= new YouTubePlayerTracker();
                                    youTubePlayer.addListener(tracker);
                                    Log.e(TAG, "onReady: "+tracker.getState());
                                    Log.e(TAG, "onReady: "+tracker.getVideoDuration());
                                    Log.e(TAG, "onReady: " + video.getKey());
                                }

                                @Override
                                public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
                                    super.onStateChange(youTubePlayer, state);
                                }

                                @Override
                                public void onError(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerError error) {
                                    super.onError(youTubePlayer, error);
                                    Log.e(TAG, "onError: " + youTubePlayer + error);
                                }
                            });


//                        binding.vvTrailer.initialize(Const.info.utubeKey, new YouTubePlayer.OnInitializedListener() {
//                            @Override
//                            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                                youTubePlayer.loadVideo(video.getKey(), 0);
//                            }
//
//                            @Override
//                            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//                            }
//                        });
                    }
                }
            }
        });
        getParentFragmentManager().setFragmentResultListener("dataFromDetail", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                data = result.getInt("idFromDetail");
                if (data != 0) {
                    videoViewModel.requestVideo(data);
                }
                Log.e(TAG, "onFragmentResult: " + data);
            }
        });

        return binding.getRoot();
    }
}
