package com.example.phim88.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.example.phim88.R;
import com.example.phim88.databinding.FragmentDetailBinding;
import com.example.phim88.model.detail.Genre;
import com.example.phim88.view.adapter.MyViewPagerAdapter;
import com.example.phim88.viewmodel.DetailViewModel;
import com.example.phim88.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DetailFragment extends BaseFragment {

    private static final String TAG = "DetailFragment";
    private final List<String> genres = new ArrayList<>();
    public int movie_id;
    private int id;
    private int mId;
    private MyViewPagerAdapter myViewPagerAdapter;
    private FragmentDetailBinding binding;
    private DetailViewModel detailViewModel;
    private SharedViewModel sharedViewModel;
    private SkeletonScreen skeletonScreen;
    private Executor executor;
    private ExecutorService executorService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        myViewPagerAdapter = new MyViewPagerAdapter(getActivity().getSupportFragmentManager(), 3);
        binding.viewPager.setAdapter(myViewPagerAdapter);
//        binding.viewPager.setOffscreenPageLimit(3);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        myViewPagerAdapter.notifyDataSetChanged();
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        id = getArguments().getInt("id");
        mId = getArguments().getInt("mId");
        Log.e(TAG, "fetchVideo: " + id);
        Bundle bundle = new Bundle();
        bundle.putInt("idFromDetail", id);
        getParentFragmentManager().setFragmentResult("dataFromDetail", bundle);


        fetchDetail();


        binding.detailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.onBackPressed();
            }
        });

        Log.e(TAG, "onCreateView: " + movie_id);
        skeletonScreen = Skeleton.bind(binding.frDetail)
                .load(R.layout.tv_overview_item)
                .duration(500)
                .angle(0)
                .show();
//        skeletonScreen1 = Skeleton.bind(binding.tvGenres)
//                .load(R.layout.tv_overview_item)
//                .show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                skeletonScreen.hide();
//                skeletonScreen1.hide();
            }
        },1000);
        return binding.getRoot();
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        Log.e(TAG, "123231231231231111: " + id);
//        sharedViewModel.setData(id);
//    }

    public void fetchDetail() {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        Log.e(TAG, "123231231231231111: " + id);
        if (id != 0){
            sharedViewModel.setData(id);
            detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
            detailViewModel.RequestListDetail(id);
            String backdrop = getArguments().getString("backdrop");
            String img = getArguments().getString("img");
            String title = getArguments().getString("title");
//        String overview = getArguments().getString("overview");
            boolean adult = getArguments().getBoolean("adult");
            if (adult == true) {
                binding.tvAge.setText("TV 18+");
            } else {
                binding.tvAge.setText("TV 16+");
            }
            float voteCount = getArguments().getFloat("voteAverage") * 10;
            String img_base = "https://image.tmdb.org/t/p/original";
            int genreIds = getArguments().getInt("genreIds");
            Log.e(TAG, "fetchImg: " + genreIds);

            binding.ratingBar.setRating(getArguments().getFloat("voteAverage") / 2);

            Glide.with(this)
                    .load(img_base + img)
                    .centerCrop()
                    .into(binding.imageView2);
            Glide.with(this)
                    .load(img_base + backdrop)
                    .centerCrop()
                    .into(binding.imgBackdrop);
            binding.tvLike.setText(Math.round(voteCount) + "%");
            binding.tvMovieName.setText(title);
        } else if (mId != 0){
            sharedViewModel.setData(mId);
            detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
            detailViewModel.RequestListDetail(mId);
            detailViewModel.getLiveData().observe(getViewLifecycleOwner(), detail -> {
                String backdrop = detail.getBackdropPath();
                String img = detail.getPosterPath();
                String title = detail.getTitle();
//        String overview = getArguments().getString("overview");
                boolean adult = detail.getAdult();
                if (adult == true) {
                    binding.tvAge.setText("TV 18+");
                } else {
                    binding.tvAge.setText("TV 16+");
                }
                float voteCount = detail.getVoteAverage() * 10;
                String img_base = "https://image.tmdb.org/t/p/original";
                binding.ratingBar.setRating(detail.getVoteAverage() / 2);
                Glide.with(this)
                        .load(img_base + img)
                        .centerCrop()
                        .into(binding.imageView2);
                Glide.with(this)
                        .load(img_base + backdrop)
                        .centerCrop()
                        .into(binding.imgBackdrop);
                binding.tvLike.setText(Math.round(voteCount) + "%");
                binding.tvMovieName.setText(title);
                Log.e(TAG, "fetchDetail: "+detail.getAdult()+" " + detail.getVideo()+ " "+detail.getBackdropPath()+" "+detail.getId());
            });
        }

        detailViewModel.getLiveData().observe(getViewLifecycleOwner(), detail -> {
            for (Genre genre : detail.getGenres()) {
                genres.add(genre.getName());
            }

            binding.tvOverview.setText(detail.getOverview());
            binding.tvGenres.setText(String.valueOf(genres).replace("[", "").replace("]", ""));
        });
    }
}

