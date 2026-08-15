package com.example.clusterapp;

public class Cluster {
    private String name;
    private String description;
    private int memberCount;

    public Cluster(String name, String description, int memberCount) {
        this.name = name;
        this.description = description;
        this.memberCount = memberCount;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getMemberCount() { return memberCount; }
}