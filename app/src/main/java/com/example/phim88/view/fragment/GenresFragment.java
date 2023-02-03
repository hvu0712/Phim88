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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.example.phim88.R;
import com.example.phim88.databinding.FragmentGenresBinding;
import com.example.phim88.model.detail.Detail;
import com.example.phim88.model.detail.Genre;
import com.example.phim88.model.detail.ProductionCompany;
import com.example.phim88.model.discover.Result;
import com.example.phim88.view.adapter.GenresAdapter;
import com.example.phim88.view.adapter.MoreAdapter;
import com.example.phim88.viewmodel.DetailViewModel;
import com.example.phim88.viewmodel.DiscoverViewModel;
import com.example.phim88.viewmodel.SharedViewModel;

public class GenresFragment extends BaseFragment{
    private FragmentGenresBinding binding;
    private GetName getData;
    private GetId getId;
    private MoreAdapter moreAdapter;
    private DiscoverViewModel discoverViewModel;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private static final String TAG = "GenresFragment";
    private SkeletonScreen skeletonScreen;

    public void setGetId(GetId getId) {
        this.getId = getId;
    }

    public void setGetData(GetName getData) {
        this.getData = getData;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGenresBinding.inflate(inflater, container, false);

        moreAdapter = new MoreAdapter(getContext());
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        binding.tvTitle.setTitle(getData.data());
        binding.tvTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                activity.onBackPressed();
            }
        });
        fetchData(getId.id());


        return binding.getRoot();
    }

    private void fetchData(int with_genres) {
       discoverViewModel = new ViewModelProvider(this).get(DiscoverViewModel.class);
       discoverViewModel.getMutableLiveData().observe(requireActivity(), discover -> {
           moreAdapter.setResultList(discover);
           for (Result result : discover){
               Log.e(TAG, "discover: "+result.getTitle()+" "+result.getId());
           }
           binding.rclvGenres.setLayoutManager(staggeredGridLayoutManager);
           binding.rclvGenres.setAdapter(moreAdapter);
           skeletonScreen = Skeleton.bind(binding.rclvGenres)
                   .adapter(moreAdapter)
                   .load(R.layout.more_item_skeleton)
                   .duration(500)
                   .angle(0)
                   .show();
           Handler handler = new Handler();
           handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   skeletonScreen.hide();
               }
           },1000);
       });
       discoverViewModel.requestDiscover(with_genres);
    }

    public interface GetName{
        String data();
    }
    public interface GetId{
        int id();
    }
}
