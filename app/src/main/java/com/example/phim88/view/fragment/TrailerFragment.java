package com.example.phim88.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentTrailerBinding;
import com.example.phim88.model.Video.Video;
import com.example.phim88.viewmodel.SharedViewModel;
import com.example.phim88.viewmodel.VideoViewModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;
import java.util.List;

public class TrailerFragment extends BaseFragment {

    private static final String TAG = "TrailerFragment";
    private final List<Video> listVideo = new ArrayList<>();
    public int data;
    private FragmentTrailerBinding binding;
    private VideoViewModel videoViewModel;
    private static String a;
    private SharedViewModel sharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trailer, container, false);

        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        videoViewModel.getListVideo().observe(getViewLifecycleOwner(), videos -> {
            if (videos.size() > 0 && videos != null) {
                for (Video video : videos) {
                    Log.e(TAG, "a: " + video.getKey());
                    if (video.getName().equals("Official Trailer")) {
                        a = video.getKey();
                        Log.e(TAG, "a: " + a);
                    } else {
                        a = video.getKey();
                    }
                }
            }
        });
        binding.videoViewTrailer.initialize(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                Log.e(TAG, "onReady: "+a);
                youTubePlayer.loadVideo(a, 0f);
            }
        });
        Log.e(TAG, "trailerFragment: " + a);


        return binding.getRoot();
    }

}
