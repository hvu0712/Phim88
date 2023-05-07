package com.example.phim88.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phim88.databinding.ItemTrailerBinding;
import com.example.phim88.model.video.Video;
import com.example.phim88.view.activity.MainActivity2;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
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
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Video video = videoList.get(position);
        if (video == null) {
            return;
        }
        Log.e("TAG", "onBindViewHolder: "+videoList.get(0).getKey() + " " + videoList.get(0).getName());
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
//        holder.binding.youTubePlayerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(view.getContext(), MainActivity2.class);
//                holder.binding.getRoot().getContext().startActivity(intent);
//            }
//        });
        try {
            IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(0).build();
            holder.binding.youTubePlayerView.initialize(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    youTubePlayer.cueVideo(video.getKey(), 0f);
                    DefaultPlayerUiController defaultPlayerUiController = new DefaultPlayerUiController(holder.binding.youTubePlayerView, youTubePlayer);
                    defaultPlayerUiController.setFullScreenButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String k = video.getKey();
                            Intent intent = new Intent(holder.binding.getRoot().getContext(), MainActivity2.class);
                            if (k != null && k.length() > 0) {
                                intent.putExtra("key", k);
                            }

                            holder.binding.getRoot().getContext().startActivity(intent);
                        }
                    });
                    holder.binding.youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.getRootView());
                }
            }, options);


//            holder.binding.youTubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
//                @Override
//                public void onYouTubePlayerEnterFullScreen() {
//                    holder.binding.youTubePlayerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//                }
//
//                @Override
//                public void onYouTubePlayerExitFullScreen() {
//
//                }
//            });
        } catch (Exception e) {
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
