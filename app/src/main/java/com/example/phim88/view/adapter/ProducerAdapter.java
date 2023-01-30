package com.example.phim88.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phim88.databinding.ProducerItemBinding;
import com.example.phim88.model.detail.Detail;
import com.example.phim88.model.detail.ProductionCompany;

import java.util.ArrayList;
import java.util.List;

public class ProducerAdapter extends RecyclerView.Adapter<ProducerAdapter.ProducerViewHolder> {
    private Context context;
    private List<ProductionCompany> productionCompanyList;

    public void setProductionCompanyList(List<ProductionCompany> productionCompanyList) {
        this.productionCompanyList = productionCompanyList;
    }

    public ProducerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProducerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProducerItemBinding binding = ProducerItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ProducerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProducerViewHolder holder, int position) {
        String img_base = "https://image.tmdb.org/t/p/original/";
        ProductionCompany productionCompany = productionCompanyList.get(position);
        Glide.with(context)
                .load(img_base+productionCompany.getLogoPath())
                .into(holder.binding.imgItemProducer);
        holder.binding.tvNameProducer.setText(productionCompany.getName());
    }

    @Override
    public int getItemCount() {
        if (productionCompanyList != null){
            return productionCompanyList.size();
        }
        return 0;
    }

    public class ProducerViewHolder extends RecyclerView.ViewHolder {
        private ProducerItemBinding binding;
        public ProducerViewHolder(@NonNull ProducerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
