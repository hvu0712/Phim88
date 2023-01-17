package com.example.phim88.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentProducerBinding;
import com.example.phim88.model.detail.ProductionCompany;
import com.example.phim88.viewmodel.DetailViewModel;
import com.example.phim88.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProducerFragment extends Fragment {

    private FragmentProducerBinding binding;
    private DetailViewModel detailViewModel;
    private List<ProductionCompany> productionCompanyList = new ArrayList<>();
    private SharedViewModel sharedViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProducerBinding.inflate(inflater, container, false);

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 2000);
        fetchData();

        return binding.getRoot();
    }
    private void fetchData(){
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.getListProductionCompany().observe(getViewLifecycleOwner(), productionCompanies -> {
//            productionCompanyList.addAll(productionCompanies);
            if (productionCompanies.size() > 0 && productionCompanies != null){
                Log.e("TAG", "fetchData1: "+productionCompanies);
            }
        });
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getmData().observe(getViewLifecycleOwner(), integer -> {
            Log.e("TAG", "fetchData: "+integer);
            detailViewModel.RequestListDetail(integer);
        });
    }
}
