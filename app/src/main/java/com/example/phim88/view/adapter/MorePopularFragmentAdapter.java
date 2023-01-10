package com.example.phim88.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phim88.databinding.MorePopularFragmentBinding;

public class MorePopularFragmentAdapter extends RecyclerView.Adapter<MorePopularFragmentAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public MorePopularFragmentBinding binding;

        public ViewHolder(@NonNull MorePopularFragmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
