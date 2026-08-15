package com.example.clusterapp;

public interface AuthResultListener {
    void onSuccess(String email);
    void onFailure(String message);
}
