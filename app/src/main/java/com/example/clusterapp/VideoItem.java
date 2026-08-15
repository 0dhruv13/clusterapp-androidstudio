package com.example.clusterapp;

public class VideoItem {
    private String title;
    private String videoUrl;
    private int videoResId;
    private String gameName;
    private boolean isLocal;

    public VideoItem(String title, int videoResId, String gameName) {
        this.title = title;
        this.videoResId = videoResId;
        this.gameName = gameName;
        this.isLocal = true;
    }

    public VideoItem(String title, String videoUrl, String gameName) {
        this.title = title;
        this.videoUrl = videoUrl;
        this.gameName = gameName;
        this.isLocal = false;
    }

    public String getTitle() { return title; }
    public String getVideoUrl() { return videoUrl; }
    public int getVideoResId() { return videoResId; }
    public String getGameName() { return gameName; }
    public boolean isLocal() { return isLocal; }
}