package com.example.clusterapp;

import android.content.Context;
import android.content.SharedPreferences;

public class MockAuthService {
    private static final String PREF_NAME = "MockAuthPrefs";
    private static final String KEY_USER_PREFIX = "user_";
    private static final String KEY_CURRENT_USER = "current_user";
    private static final String KEY_PROFILE_IMAGE_PREFIX = "profile_image_";
    
    private final SharedPreferences prefs;
    private static MockAuthService instance;

    private MockAuthService(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized MockAuthService getInstance(Context context) {
        if (instance == null) {
            instance = new MockAuthService(context);
        }
        return instance;
    }

    public boolean register(String email, String password) {
        if (prefs.contains(KEY_USER_PREFIX + email)) {
            return false; // User already exists
        }
        prefs.edit().putString(KEY_USER_PREFIX + email, password).apply();
        setCurrentUser(email);
        return true;
    }

    public boolean login(String email, String password) {
        String savedPassword = prefs.getString(KEY_USER_PREFIX + email, null);
        if (savedPassword != null && savedPassword.equals(password)) {
            setCurrentUser(email);
            return true;
        }
        return false;
    }

    public void logout() {
        prefs.edit().remove(KEY_CURRENT_USER).apply();
    }

    public String getCurrentUser() {
        return prefs.getString(KEY_CURRENT_USER, null);
    }

    private void setCurrentUser(String email) {
        prefs.edit().putString(KEY_CURRENT_USER, email).apply();
    }

    public void setProfileImage(String email, String uri) {
        prefs.edit().putString(KEY_PROFILE_IMAGE_PREFIX + email, uri).apply();
    }

    public String getProfileImage(String email) {
        return prefs.getString(KEY_PROFILE_IMAGE_PREFIX + email, null);
    }
}