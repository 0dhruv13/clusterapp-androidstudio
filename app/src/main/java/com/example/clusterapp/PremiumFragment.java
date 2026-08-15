package com.example.clusterapp;

import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.razorpay.Checkout;

import org.json.JSONObject;

public class PremiumFragment extends Fragment {

    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_premium, container, false);

        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().hide();
            }
            BottomNavigationView bottomNav = activity.findViewById(R.id.bottom_navigation);
            if (bottomNav != null) {
                bottomNav.setVisibility(View.GONE);
            }
            
            // Go truly full screen: hide status bar and navigation bar
            Window window = activity.getWindow();
            window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        videoView = view.findViewById(R.id.premiumVideoView);
        Button subscribeButton = view.findViewById(R.id.subscribeButton);

        setupBackgroundVideo();

        subscribeButton.setOnClickListener(v -> startPayment());

        return view;
    }

    private void setupBackgroundVideo() {
        String videoPath = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.premium_bg;
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            
            videoView.post(() -> {
                if (getActivity() == null) return;

                // Get absolute screen dimensions (including system bar areas)
                DisplayMetrics metrics = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
                int screenWidth = metrics.widthPixels;
                int screenHeight = metrics.heightPixels;

                // To "zoom out" but cover vertically:
                // We set height to 100% of the screen and calculate width based on aspect ratio
                int videoWidth = mp.getVideoWidth();
                int videoHeight = mp.getVideoHeight();
                float videoRatio = (float) videoWidth / videoHeight;

                ViewGroup.LayoutParams lp = videoView.getLayoutParams();
                lp.height = screenHeight;
                lp.width = (int) (screenHeight * videoRatio);
                
                // If the resulting width is still less than screen width (very tall video),
                // we must scale by width instead to ensure full coverage.
                if (lp.width < screenWidth) {
                    lp.width = screenWidth;
                    lp.height = (int) (screenWidth / videoRatio);
                }

                videoView.setLayoutParams(lp);
                
                // Center the video so the "zoom out" feel is even
                videoView.setTranslationX((screenWidth - lp.width) / 2f);
                videoView.setTranslationY((screenHeight - lp.height) / 2f);
                
                videoView.start();
            });
        });
    }

    private void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_TQBXcYxB6JdICY");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Cluster App");
            options.put("description", "Premium Subscription");
            options.put("theme.color", "#FFD700");
            options.put("currency", "INR");
            options.put("amount", "99900"); // Amount in paise (999 INR)
            options.put("prefill.email", "user@example.com");
            options.put("prefill.contact", "9876543210");

            checkout.open(requireActivity(), options);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().show();
            }
            BottomNavigationView bottomNav = activity.findViewById(R.id.bottom_navigation);
            if (bottomNav != null) {
                bottomNav.setVisibility(View.VISIBLE);
            }
            
            // Restore system bars
            Window window = activity.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (videoView != null) {
            videoView.start();
        }
    }
}