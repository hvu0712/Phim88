package com.example.phim88.view.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.phim88.R;
import com.example.phim88.databinding.FragmentDetailBinding;
import com.example.phim88.databinding.FragmentTrailerBinding;
import com.example.phim88.model.Video.Video;
import com.example.phim88.model.detail.Genre;
import com.example.phim88.model.popular.Popular;
import com.example.phim88.view.activity.MainActivity;
import com.example.phim88.view.adapter.MovieAdapter;
import com.example.phim88.view.adapter.MyViewPagerAdapter;
import com.example.phim88.viewmodel.DetailViewModel;
import com.example.phim88.viewmodel.PopularViewModel;
import com.example.phim88.viewmodel.VideoViewModel;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;
import java.util.List;


public class DetailFragment extends BaseFragment {

    private int id;
    Bundle bundle;
    private List<String> genres = new ArrayList<>();
    private List<Video> listVideo = new ArrayList<>();
    private MyViewPagerAdapter myViewPagerAdapter;
    private VideoViewModel videoViewModel;
    private static final String TAG = "DetailFragment";
    private FragmentDetailBinding binding;
    private DetailViewModel detailViewModel;

    List<String> list = new ArrayList<>();

    public int movie_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        myViewPagerAdapter = new MyViewPagerAdapter(((MainActivity) getActivity()).getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewPager.setAdapter(myViewPagerAdapter);
         bundle = new Bundle();
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        fetchDetail();
        fetchVideo();

        Log.e(TAG, "onCreateView: "+movie_id);
        return binding.getRoot();
    }


    public void fetchDetail() {
        String backdrop = getArguments().getString("backdrop");
        String img = getArguments().getString("img");
        String title = getArguments().getString("title");
//        String overview = getArguments().getString("overview");
        boolean adult = getArguments().getBoolean("adult");
        if (adult == true){
            binding.tvAge.setText("TV 18+");
        } else {
            binding.tvAge.setText("TV 16+");
        }
        float voteCount = getArguments().getFloat("voteAverage")*10;
        String img_base = "https://image.tmdb.org/t/p/original";
        int genreIds = getArguments().getInt("genreIds");
        Log.e(TAG, "fetchImg: "+genreIds);
        int id = getArguments().getInt("id");
        binding.ratingBar.setRating(getArguments().getFloat("voteAverage")/2);

//        popularViewModel.requestPopular();
        Glide.with(this)
                .load(img_base + img)
                .into(binding.imageView2);
        Glide.with(this)
                .load(img_base + backdrop)
                .into(binding.imgBackdrop);
        binding.tvLike.setText(Math.round(voteCount)+"%");
        binding.tvMovieName.setText(title);
//        binding.tvOverview.setText(overview);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.getListGenre().observe(getViewLifecycleOwner(), genres1 -> {
            if (genres1.size() > 0 && genres1 != null){
                for (Genre genre : genres1){
                    genres.add(genre.getName());
                }
                binding.tvGenres.setText(String.valueOf(genres).replace("[", "").replace("]",""));
            }
        });
        detailViewModel.getListDetail().observe(getViewLifecycleOwner(), details -> {
            if (details != null){
                binding.tvOverview.setText(details);
            }
        });
        detailViewModel.RequestListDetail(id);
    }

    public void fetchVideo(){
        id = getArguments().getInt("id");
//        bundle.putInt("idFromDetail", id);
//        TrailerFragment trailerFragment = new TrailerFragment();
//        trailerFragment.setArguments(bundle);
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container, trailerFragment)
//                .commit();
        Log.e(TAG, "fetchVideo: "+id);
        Bundle bundle = new Bundle();
        bundle.putInt("idFromDetail", id);
        getParentFragmentManager().setFragmentResult("dataFromDetail", bundle);

        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        videoViewModel.setId(id);
        videoViewModel.getListVideo().observe(getViewLifecycleOwner(), videos -> {
            if (videos.size() > 0 && videos != null){
                for (Video video : videos){
                    if (video.getName().equals("Official Trailer") == true){
                        listVideo.add(new Video(video.getKey()));
                    }
                }
            }
        });
        videoViewModel.requestVideo(id);
    }

}
