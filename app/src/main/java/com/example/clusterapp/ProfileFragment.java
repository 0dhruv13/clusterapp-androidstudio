package com.example.clusterapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private ImageView profileImage;
    private FirebaseAuthService authService;
    private MockAuthService mockAuthService;
    private String currentUser;

    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    if (imageUri != null) {
                        profileImage.setImageURI(imageUri);
                        mockAuthService.setProfileImage(currentUser, imageUri.toString());
                    }
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        authService = FirebaseAuthService.getInstance();
        mockAuthService = MockAuthService.getInstance(requireContext());
        currentUser = authService.getCurrentUserEmail();

        profileImage = view.findViewById(R.id.profileImage);
        TextView changePhoto = view.findViewById(R.id.changePhotoText);
        TextView emailText = view.findViewById(R.id.userEmailText);
        Button logoutBtn = view.findViewById(R.id.logoutBtn);

        if (currentUser != null) {
            emailText.setText(currentUser);
            String savedImageUri = mockAuthService.getProfileImage(currentUser);
            if (savedImageUri != null) {
                profileImage.setImageURI(Uri.parse(savedImageUri));
            }
        }

        changePhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            pickImageLauncher.launch(intent);
        });

        logoutBtn.setOnClickListener(v -> {
            authService.logout();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        return view;
    }
}
