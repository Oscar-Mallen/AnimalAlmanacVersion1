package com.example.animalalmanacversion1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find and initialize BottomNavigationView
        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        if (navView == null) {
            Log.e(TAG, "BottomNavigationView is null. Please check if the ID is correct.");
            return;
        }

        // Initialize NavHostFragment and NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment == null) {
            Log.e(TAG, "NavHostFragment is null. Please check if the ID is correct.");
            return;
        }

        NavController navController = navHostFragment.getNavController();

        // Set up navigation with bottom navigation view
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.savedFragment, R.id.loginFragment)
                .build();

        NavigationUI.setupWithNavController(navView, navController);
        Log.d(TAG, "BottomNavigationView and NavController set up successfully.");

        // Handle the logout action manually
        navView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                navController.navigate(R.id.homeFragment);
                return true;
            } else if (itemId == R.id.navigation_saved) {
                navController.navigate(R.id.savedFragment);
                return true;
            } else if (itemId == R.id.navigation_logout) {
                // Handle logout here
                performLogout(navController);
                return true;
            } else {
                return false;
            }
        });


        // Check if user is logged in
        if (!isLoggedIn()) {
            Log.d(TAG, "User is not logged in. Navigating to LoginFragment.");
            // Navigate to LoginFragment if the user is not logged in
            navController.navigate(R.id.loginFragment);
        }
    }

    // Method to perform logout
    private void performLogout(NavController navController) {
        Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show();
        // Clear login status from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
        // Navigate to the login screen
        navController.navigate(R.id.loginFragment);
    }

    // Method to check if user is logged in
    private boolean isLoggedIn() {
        // Check login status from SharedPreferences or another persistent storage
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        boolean loggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        Log.d(TAG, "isLoggedIn: " + loggedIn);
        return loggedIn;
    }
}
