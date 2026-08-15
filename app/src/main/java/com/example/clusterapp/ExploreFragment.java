package com.example.clusterapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.videoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<VideoItem> videos = new ArrayList<>();
        // Local Gaming Trailers
        videos.add(new VideoItem("Grand Theft Auto VI Trailer 1", R.raw.gta6, "GTA 6"));
        videos.add(new VideoItem("Elden Ring Shadow of the Erdtree", R.raw.eldenring, "Elden Ring"));
        videos.add(new VideoItem("God of War Ragnarok - PC Announcement", R.raw.gow, "God of War"));
        videos.add(new VideoItem("Lords of the Fallen 2 Official Trailer", R.raw.tlotf2, "Lords of the Fallen 2"));
        videos.add(new VideoItem("Days Gone World Trailer", R.raw.daysgone, "Days Gone"));

        VideoAdapter adapter = new VideoAdapter(videos);
        recyclerView.setAdapter(adapter);

        return view;
    }
}