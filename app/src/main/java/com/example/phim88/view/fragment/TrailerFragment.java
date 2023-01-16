package com.example.phim88.view.fragment;


import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentTrailerBinding;
import com.example.phim88.model.video.Video;
import com.example.phim88.view.adapter.TrailerAdapter;
import com.example.phim88.viewmodel.SharedViewModel;
import com.example.phim88.viewmodel.VideoViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrailerFragment extends BaseFragment {

    private static final String TAG = "TrailerFragment";
    public int data;
    private FragmentTrailerBinding binding;
    private VideoViewModel videoViewModel;
    private static String a;
    private SharedViewModel sharedViewModel;
    private TrailerAdapter trailerAdapter;
    private List<Video> videoList;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentTrailerBinding.inflate(inflater, container, false);

        videoList = new ArrayList<>();
        trailerAdapter = new TrailerAdapter(getContext());
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        binding.rcvTrailer.setLayoutManager(staggeredGridLayoutManager);

        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        videoViewModel.getListVideo().observe(getViewLifecycleOwner(), videos -> {
            if (videos.size() > 0 && videos != null && videos.size() > 0) {
                videoList.addAll(videos);
//                trailerAdapter.setVideoList(videos);
                Log.e(TAG, "sech: "+videos.size());
                for (Video video : videos) {
                    Log.e(TAG, "xech: " + video.getName());
                    if (video.getName().equals("Official Trailer")) {
                        a = video.getKey();
                        Log.e(TAG, "sech: " + a);
                    } else {
                        a = video.getKey();
                    }
                }
                videoList.clear();
                videoList.addAll(videos);
                trailerAdapter.setVideoList(videoList);
                binding.rcvTrailer.setAdapter(trailerAdapter);
                Log.e(TAG, "xeck2: "+trailerAdapter.getItemCount()+" "+videoList.size() +" "+trailerAdapter.getVideoList().size()+ " "+videos.size());
            }
        });
//        getParentFragmentManager().setFragmentResultListener("dataFromDetail", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                data = result.getInt("idFromDetail");
//                if (data != 0) {
//                    videoViewModel.requestVideo(data);
//                }
//                Log.e(TAG, "onFragmentResult: " + data);
//            }
//        });
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getmData().observe(getViewLifecycleOwner(), integer -> {
            videoViewModel.requestVideo(integer);
        });



//        IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(0).build();
//        binding.videoViewTrailer.initialize(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                super.onReady(youTubePlayer);
//                Log.e(TAG, "onReady: "+a);
//                DefaultPlayerUiController defaultPlayerUiController = new DefaultPlayerUiController(binding.videoViewTrailer, youTubePlayer);
//                youTubePlayer.cueVideo(a, 0f);
//                binding.videoViewTrailer.setCustomPlayerUi(defaultPlayerUiController.getRootView());
//            }
//        }, options);

//        binding.videoViewTrailer.toggleFullScreen();



        return binding.getRoot();
    }
}
