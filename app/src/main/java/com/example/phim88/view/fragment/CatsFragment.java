package com.example.phim88.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentCastsBinding;
import com.example.phim88.model.cast.Cast;
import com.example.phim88.view.adapter.CastAdapter;
import com.example.phim88.viewmodel.CreditsViewModel;
import com.example.phim88.viewmodel.SharedViewModel;

public class CatsFragment extends Fragment {

    private static final String TAG = "CatsFragment";
    private FragmentCastsBinding binding;
    private CreditsViewModel creditsViewModel;
    public int data;
    private DetailFragment detailFragment;
    private SharedViewModel sharedViewModel;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private CastAdapter castAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCastsBinding.inflate(inflater, container, false);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        castAdapter = new CastAdapter(getContext());

        creditsViewModel = new ViewModelProvider(this).get(CreditsViewModel.class);
        creditsViewModel.getListCast().observe(getViewLifecycleOwner(), casts -> {
            if (casts.size() > 0 && casts != null){
                for (Cast cast : casts){
                    Log.e(TAG, "onCreateView123: "+ cast.getName()+ cast.getProfilePath());
                }
                binding.rcvCast.setLayoutManager(staggeredGridLayoutManager);
                castAdapter.setCasts(casts);
                binding.rcvCast.setAdapter(castAdapter);
                castAdapter.notifyDataSetChanged();
            }
        });
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getmData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                creditsViewModel.requestCast(integer);
            }
        });


//        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
//        sharedViewModel.getmData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                Log.e(TAG, "12323123123123111122222: "+integer);
//            }
//        });


//        getParentFragmentManager().setFragmentResultListener("dataFromDetail", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                data = result.getInt("idFromDetail");
//                if (data != 0) {
//                    creditsViewModel.requestCast(data);
//                }
//                Log.e(TAG, "onFragmentResult: " + data);
//            }
//        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
