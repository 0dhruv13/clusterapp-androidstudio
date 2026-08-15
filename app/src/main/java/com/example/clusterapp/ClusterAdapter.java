package com.example.clusterapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.content.Intent;

public class ClusterAdapter extends RecyclerView.Adapter<ClusterAdapter.ViewHolder> {

    private List<Cluster> clusterList;

    public ClusterAdapter(List<Cluster> clusterList) {
        this.clusterList = clusterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cluster, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cluster cluster = clusterList.get(position);
        holder.name.setText(cluster.getName());
        holder.description.setText(cluster.getDescription());
        holder.members.setText(holder.itemView.getContext().getString(R.string.members_count, cluster.getMemberCount()));
        
        holder.joinBtn.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ChatActivity.class);
            intent.putExtra("community_name", cluster.getName());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return clusterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, members;
        Button joinBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.clusterName);
            description = itemView.findViewById(R.id.clusterDescription);
            members = itemView.findViewById(R.id.memberCount);
            joinBtn = itemView.findViewById(R.id.joinButton);
        }
    }
}