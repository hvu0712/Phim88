package com.example.phim88.view.fragment;

import android.os.Bundle;
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
import com.example.phim88.R;
import com.example.phim88.databinding.FragmentDetailBinding;
import com.example.phim88.model.detail.Genre;
import com.example.phim88.model.detail.ProductionCompany;
import com.example.phim88.model.video.Video;
import com.example.phim88.view.adapter.MyViewPagerAdapter;
import com.example.phim88.viewmodel.CreditsViewModel;
import com.example.phim88.viewmodel.DetailViewModel;
import com.example.phim88.viewmodel.SharedViewModel;
import com.example.phim88.viewmodel.VideoViewModel;

import java.util.ArrayList;
import java.util.List;


public class DetailFragment extends BaseFragment {

    private static final String TAG = "DetailFragment";
    private final List<String> genres = new ArrayList<>();
    private final List<Video> listVideo = new ArrayList<>();
    public int movie_id;
    private int id;
    private MyViewPagerAdapter myViewPagerAdapter;
    private VideoViewModel videoViewModel;
    private FragmentDetailBinding binding;
    private DetailViewModel detailViewModel;
    private SharedViewModel sharedViewModel;
    private CreditsViewModel creditsViewModel;


    public int data;

    public int getData() {
        return data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);


        myViewPagerAdapter = new MyViewPagerAdapter(getActivity().getSupportFragmentManager(), 3);
        binding.viewPager.setAdapter(myViewPagerAdapter);
        binding.viewPager.setOffscreenPageLimit(3);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        myViewPagerAdapter.notifyDataSetChanged();

        id = getArguments().getInt("id");
        Log.e(TAG, "fetchVideo: " + id);
        Bundle bundle = new Bundle();
        bundle.putInt("idFromDetail", id);
        getParentFragmentManager().setFragmentResult("dataFromDetail", bundle);


        fetchDetail();
//        fetchVideo();
//        fetchData();


        binding.detailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.onBackPressed();
            }
        });

        Log.e(TAG, "onCreateView: " + movie_id);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        Log.e(TAG, "123231231231231111: " + id);
        sharedViewModel.setData(id);
    }

    public void fetchDetail() {
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
        int id = getArguments().getInt("id");
        binding.ratingBar.setRating(getArguments().getFloat("voteAverage") / 2);

//        popularViewModel.requestPopular();
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
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.getListGenre().observe(getViewLifecycleOwner(), genres1 -> {
            if (genres1.size() > 0 && genres1 != null) {
                for (Genre genre : genres1) {
                    genres.add(genre.getName());
                }
                binding.tvGenres.setText(String.valueOf(genres).replace("[", "").replace("]", ""));
            }
        });
        detailViewModel.getListDetail().observe(getViewLifecycleOwner(), details -> {
            if (details != null) {
                binding.tvOverview.setText(details);
            }
        });
        detailViewModel.RequestListDetail(id);
    }

    //    public void fetchVideo() {
//
////        bundle.putInt("idFromDetail", id);
////        TrailerFragment trailerFragment = new TrailerFragment();
////        trailerFragment.setArguments(bundle);
////        getFragmentManager()
////                .beginTransaction()
////                .replace(R.id.fragment_container, trailerFragment)
////                .commit();
//
//
//        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
////        videoViewModel.setId(id);
//        videoViewModel.getListVideo().observe(getViewLifecycleOwner(), videos -> {
//            if (videos.size() > 0 && videos != null){
//                for (Video video : videos){
//                    if (video.getName().equals("Official Trailer") == true){
//                        listVideo.add(new Video(video.getKey()));
//                    }
//                }
//            }
//        });
//        videoViewModel.requestVideo(id);
//    }
    private List<ProductionCompany> productionCompanyList = new ArrayList<>();

    private void fetchData() {
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.getListProductionCompany().observe(getViewLifecycleOwner(), productionCompanies -> {
            productionCompanyList.addAll(productionCompanies);
             Log.e("TAG", "fetchData: " + productionCompanies);
        });
        sharedViewModel.getmData().observe(getViewLifecycleOwner(), integer -> {
            detailViewModel.RequestListDetail(integer);
        });
    }
}
