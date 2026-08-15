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

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.clusterRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Cluster> clusters = new ArrayList<>();
        clusters.add(new Cluster("Tech Innovators", "Building the future with AI and code.", 1250));
        clusters.add(new Cluster("Outdoor Adventures", "Weekend hiking and camping trips.", 430));
        clusters.add(new Cluster("Creative Writers", "Sharing stories and poetry daily.", 890));
        clusters.add(new Cluster("Fitness Junkies", "Gym motivation and diet tips.", 2100));
        clusters.add(new Cluster("Gaming Legends", "Latest updates and multiplayer sessions.", 5600));

        ClusterAdapter adapter = new ClusterAdapter(clusters);
        recyclerView.setAdapter(adapter);

        return view;
    }
}