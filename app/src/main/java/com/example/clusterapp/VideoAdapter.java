package com.example.clusterapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<VideoItem> videoList;
    private boolean isMuted = true;

    public VideoAdapter(List<VideoItem> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoItem item = videoList.get(position);
        holder.title.setText(item.getTitle());
        holder.gameName.setText(item.getGameName());

        holder.progressBar.setVisibility(View.VISIBLE);
        holder.videoView.stopPlayback();

        if (item.isLocal()) {
            String path = "android.resource://" + holder.itemView.getContext().getPackageName() + "/" + item.getVideoResId();
            holder.videoView.setVideoURI(Uri.parse(path));
        } else {
            holder.videoView.setVideoURI(Uri.parse(item.getVideoUrl()));
        }

        holder.videoView.setOnPreparedListener(mp -> {
            holder.progressBar.setVisibility(View.GONE);
            mp.setLooping(true);
            
            // Set initial state
            float volume = isMuted ? 0f : 1f;
            mp.setVolume(volume, volume);
            holder.muteButton.setImageResource(isMuted ? R.drawable.ic_volume_off : R.drawable.ic_volume_up);

            holder.muteButton.setOnClickListener(v -> {
                isMuted = !isMuted;
                float newVolume = isMuted ? 0f : 1f;
                mp.setVolume(newVolume, newVolume);
                holder.muteButton.setImageResource(isMuted ? R.drawable.ic_volume_off : R.drawable.ic_volume_up);
            });

            holder.videoView.start();
        });

        holder.videoView.setOnErrorListener((mp, what, extra) -> {
            holder.progressBar.setVisibility(View.GONE);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        TextView title, gameName;
        ProgressBar progressBar;
        ImageView muteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            title = itemView.findViewById(R.id.videoTitle);
            gameName = itemView.findViewById(R.id.gameName);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
            muteButton = itemView.findViewById(R.id.muteButton);
        }
    }
}