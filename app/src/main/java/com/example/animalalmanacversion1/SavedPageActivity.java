package com.example.animalalmanacversion1;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.animalalmanacversion1.databinding.ActivitySavedPageBinding;


public class SavedPageActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
private ActivitySavedPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivitySavedPageBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        //setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_saved_page);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // LISTENER FOR HOME BUTTON NAVIGATION
        binding.homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate back with the controller
                navController.navigate(R.id.action_savedFragment_to_homeFragment);
            }
        });

        binding.savedtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.savedtitle)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_saved_page);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}