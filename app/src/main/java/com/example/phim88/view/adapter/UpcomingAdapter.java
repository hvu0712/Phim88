package com.example.phim88.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phim88.R;
import com.example.phim88.databinding.ItemMovieBinding;
import com.example.phim88.model.upcoming.Upcoming;
import com.example.phim88.view.fragment.DetailFragment;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder> {

    private List<Upcoming> upcomings;

    private Context context;

    public UpcomingAdapter(Context context) {
        this.context = context;
    }

    public void setUpcoming(List<Upcoming> upcoming) {
        this.upcomings = upcoming;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemMovieBinding binding = ItemMovieBinding.inflate(inflater, parent, false);

        return new UpcomingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingViewHolder holder, int position) {
        Upcoming upcoming = upcomings.get(position);
        if (upcoming == null) {
            return;
        }
        String img_base = "https://image.tmdb.org/t/p/w500/";
        holder.binding.tvName.setText(upcoming.getTitle());
        Glide.with(context)
                .load(img_base + upcoming.getPosterPath())
                .into(holder.binding.imgItem);
        holder.binding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment detailFragment = new DetailFragment();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out)
                        .add(R.id.fragment_container, detailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (upcomings != null) {
            return upcomings.size();
        }
        return 0;
    }

    public class UpcomingViewHolder extends RecyclerView.ViewHolder {


        public ItemMovieBinding binding;

        public UpcomingViewHolder(@NonNull ItemMovieBinding binding) {

            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
