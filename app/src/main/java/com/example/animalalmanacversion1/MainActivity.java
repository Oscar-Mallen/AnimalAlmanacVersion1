package com.example.animalalmanacversion1;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav_view);

        // Check if user is logged in
        if (!isLoggedIn()) {
            // Load LoginFragment if the user is not logged in
            loadFragment(new LoginFragment());
            return; // Exit onCreate to prevent initializing BottomNavigationView
        } else {
            // Load default fragment (e.g., HomeFragment) if user is logged in
            loadFragment(new HomeFragment());
        }

        // Handle bottom navigation item selection using lambda expression
        bottomNavView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.navigation_home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.navigation_saved) {
                selectedFragment = new SavedFragment();
            } else if (item.getItemId() == R.id.navigation_login) {
                selectedFragment = new LoginFragment();
            } else if (item.getItemId() == R.id.navigation_signup) {
                selectedFragment = new SignUpFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }
            return true;
        });
    }

    // Helper method to load fragments
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment) // R.id.fragment_container is the FrameLayout ID in activity_main.xml
                .commit();
    }

    // Method to check if user is logged in
    private boolean isLoggedIn() {
        // Check login status from SharedPreferences or another persistent storage
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }
}
