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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.phim88.R;
import com.example.phim88.databinding.FragmentProducerBinding;
import com.example.phim88.model.detail.Genre;
import com.example.phim88.model.detail.ProductionCompany;
import com.example.phim88.view.adapter.ProducerAdapter;
import com.example.phim88.viewmodel.DetailViewModel;
import com.example.phim88.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProducerFragment extends Fragment {

    private FragmentProducerBinding binding;
    private DetailViewModel detailViewModel;
    private List<ProductionCompany> productionCompanyList = new ArrayList<>();
    private SharedViewModel sharedViewModel;
    private ProducerAdapter adapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProducerBinding.inflate(inflater, container, false);
        adapter = new ProducerAdapter(getContext());
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        fetchData();
        return binding.getRoot();
    }
    private void fetchData() {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getmData().observe(getViewLifecycleOwner(), integer -> {
            detailViewModel.RequestListDetail(integer);
            Log.e("TAG", "fetchData11: " + integer);
        });
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
//        detailViewModel.getListProductionCompany().observe(getViewLifecycleOwner(), productionCompanies -> {
//            if (productionCompanies.size() > 0 && productionCompanies != null){
//                productionCompanyList.addAll(productionCompanies);
//                Log.e("TAG", "fetchData112: " + productionCompanies.get(0).getName()+" "+productionCompanyList.get(0).getName());
//            }
//        });
        detailViewModel.getLiveData().observe(getViewLifecycleOwner(), o -> {
            for (ProductionCompany productionCompany: o.getProductionCompanies()){
                Log.e("TAG", "fetchDataG: "+productionCompany.getName());
                productionCompanyList.add(productionCompany);
                adapter.setProductionCompanyList(productionCompanyList);
            }
            binding.rcvProducer.setAdapter(adapter);
            binding.rcvProducer.setLayoutManager(staggeredGridLayoutManager);
        });
    }
}
