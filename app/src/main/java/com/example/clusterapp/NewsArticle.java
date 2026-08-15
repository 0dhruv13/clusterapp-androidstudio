package com.example.clusterapp;

import java.io.Serializable;

public class NewsArticle implements Serializable {
    private String title;
    private String category;
    private String snippet;
    private String fullContent;

    public NewsArticle(String title, String category, String snippet, String fullContent) {
        this.title = title;
        this.category = category;
        this.snippet = snippet;
        this.fullContent = fullContent;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getSnippet() { return snippet; }
    public String getFullContent() { return fullContent; }
}
