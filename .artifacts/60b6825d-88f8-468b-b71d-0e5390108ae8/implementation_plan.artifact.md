# Implementation Plan: Removing Firebase and Restoring Mock Authentication

This plan removes all Firebase integration and reverts the app to using a local mock authentication system. This ensures the app is fully functional offline and avoids any server-side configuration issues.

## Proposed Changes

### [Cleanup]

#### [MODIFY] [libs.versions.toml](file:///C:/Users/dhruv/AndroidStudioProjects/clusterapp/gradle/libs.versions.toml)
- Remove `googleServices` and `firebaseBom` versions.
- Remove `firebase-bom` and `firebase-auth` library definitions.
- Remove `google-services` plugin definition.

#### [MODIFY] [build.gradle.kts (project)](file:///C:/Users/dhruv/AndroidStudioProjects/clusterapp/build.gradle.kts)
- Remove `alias(libs.plugins.google.services) apply false`.

#### [MODIFY] [build.gradle.kts (app)](file:///C:/Users/dhruv/AndroidStudioProjects/clusterapp/app/build.gradle.kts)
- Remove `alias(libs.plugins.google.services)`.
- Remove Firebase-related implementation lines.

#### [DELETE] [google-services.json](file:///C:/Users/dhruv/AndroidStudioProjects/clusterapp/app/google-services.json)
- Remove the Firebase configuration file.

### [Auth System Restoration]

#### [NEW] [MockAuthService.java](file:///C:/Users/dhruv/AndroidStudioProjects/clusterapp/app/src/main/java/com/example/clusterapp/MockAuthService.java)
- Re-implement the singleton service using `SharedPreferences` for local user storage.

#### [MODIFY] [MainActivity.java](file:///C:/Users/dhruv/AndroidStudioProjects/clusterapp/app/src/main/java/com/example/clusterapp/MainActivity.java)
- Replace `FirebaseAuth` logic with `MockAuthService`.

#### [MODIFY] [RegisterActivity.java](file:///C:/Users/dhruv/AndroidStudioProjects/clusterapp/app/src/main/java/com/example/clusterapp/RegisterActivity.java)
- Replace `FirebaseAuth` registration with `MockAuthService.register()`.
- Update error handling to be local-focused.

#### [MODIFY] [LoginActivity.java](file:///C:/Users/dhruv/AndroidStudioProjects/clusterapp/app/src/main/java/com/example/clusterapp/LoginActivity.java)
- Replace `FirebaseAuth` login with `MockAuthService.login()`.

#### [MODIFY] [HomeActivity.java](file:///C:/Users/dhruv/AndroidStudioProjects/clusterapp/app/src/main/java/com/example/clusterapp/HomeActivity.java)
- Replace `FirebaseAuth` session handling with `MockAuthService`.

#### [MODIFY] [ProfileFragment.java](file:///C:/Users/dhruv/AndroidStudioProjects/clusterapp/app/src/main/java/com/example/clusterapp/ProfileFragment.java)
- Replace `FirebaseAuth` sign-out logic with `MockAuthService.logout()`.

## Verification Plan

### Automated Tests
- Gradle Sync to ensure all Firebase dependencies are purged.
- Build project (`:app:assembleDebug`) to verify logic.

### Manual Verification
- Verify that users can register and login locally.
- Verify that the app icon remains as `wert.jpg`.
- Verify that the side navigation drawer still correctly displays the logged-in user's email.
