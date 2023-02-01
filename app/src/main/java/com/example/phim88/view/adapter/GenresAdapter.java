package com.example.phim88.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phim88.R;
import com.example.phim88.databinding.LvItemBinding;
import com.example.phim88.model.genre.Genre;
import com.example.phim88.view.fragment.GenresFragment;
import com.example.phim88.view.fragment.MorePopularFragment;
import com.example.phim88.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {

    private final List<Genre> data;
    private SharedViewModel sharedViewModel;

    public GenresAdapter() {
        data = new ArrayList<>();
    }

    public void setData(List<Genre> data) {
        if (data == null) return;
        this.data.clear();
        this.data.addAll(data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        LvItemBinding binding = LvItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre item = data.get(position);
        if (item == null) {
            return;
        }
        holder.binding.tvItem.setText(item.getName());
        holder.binding.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), item.getName(), Toast.LENGTH_SHORT).show();
                GenresFragment morePopularFragment = new GenresFragment();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in,
                                R.anim.fade_out,
                                R.anim.fade_in,
                                R.anim.slide_out)
                        .replace(R.id.fragment_container, morePopularFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public LvItemBinding binding;

        public ViewHolder(LvItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
