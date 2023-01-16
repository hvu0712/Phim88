package com.example.phim88.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phim88.databinding.ItemTrailerBinding;
import com.example.phim88.model.video.Video;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    private List<Video> videoList;
    private Context context;

    public TrailerAdapter(Context context) {
        this.context = context;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
        notifyDataSetChanged();
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemTrailerBinding binding = ItemTrailerBinding.inflate(inflater, parent, false);
        return new TrailerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Video video = videoList.get(position);
        if (video == null) {
            return;
        }
//        holder.binding.youTubePlayerView.getYouTubePlayerWhenReady(youTubePlayer -> {
//
//        });
//        holder.binding.youTubePlayerView.initialize(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                super.onReady(youTubePlayer);
//                youTubePlayer.cueVideo(video.getKey(), 0f);
//                DefaultPlayerUiController defaultPlayerUiController = new DefaultPlayerUiController(holder.binding.youTubePlayerView, youTubePlayer);
//                holder.binding.youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.getRootView());
//            }
//        }, options);
        try {
            IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(0).build();
            holder.binding.youTubePlayerView.initialize(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    youTubePlayer.cueVideo(video.getKey(), 0f);
                    DefaultPlayerUiController defaultPlayerUiController = new DefaultPlayerUiController(holder.binding.youTubePlayerView, youTubePlayer);
                    holder.binding.youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.getRootView());
                }
            }, options);

        } catch (Exception e) {
            Log.e("TAG", "xeck333: " + e);
        }
        holder.binding.tvTitleTrailer.setText(video.getName());
    }

    @Override
    public int getItemCount() {

        if (videoList != null) {
            return videoList.size();
        }
        return 0;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
        public ItemTrailerBinding binding;

        public TrailerViewHolder(@NonNull ItemTrailerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
