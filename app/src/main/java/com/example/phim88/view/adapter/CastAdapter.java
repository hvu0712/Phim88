package com.example.phim88.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phim88.databinding.CastItemBinding;
import com.example.phim88.model.cast.Cast;
import com.example.phim88.model.cast.Credits;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private Context context;
    private List<Cast> casts;

    public CastAdapter(Context context) {
        this.context = context;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CastItemBinding binding = CastItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CastViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast cast = casts.get(position);
        if (cast == null){
            return;
        }
        String img_base = "https://image.tmdb.org/t/p/w500/";
        Glide.with(context)
                .load(img_base+cast.getProfilePath())
                .into(holder.binding.imgItemCast);
        holder.binding.tvNameCast.setText(cast.getName());
    }

    @Override
    public int getItemCount() {
        if (casts != null){
            return casts.size();
        }
        return 0;
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {
        private CastItemBinding binding;
        public CastViewHolder(@NonNull CastItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
