package com.example.animalalmanacversion1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class AnimalDetailFragment extends Fragment {

    private ImageView animalImageView;
    private TextView animalNameTextView, animalDescriptionTextView;
    private Button navigateToHomeButton;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animal_detail, container, false);

        animalImageView = rootView.findViewById(R.id.animal_image_detail);
        animalNameTextView = rootView.findViewById(R.id.animal_name_detail);
        animalDescriptionTextView = rootView.findViewById(R.id.animal_description_detail);
        navigateToHomeButton = rootView.findViewById(R.id.navigateToHomeButton);  // Reference for the button

        Bundle bundle = getArguments();
        if (bundle != null) {
            animalNameTextView.setText(bundle.getString("animalName"));
            animalDescriptionTextView.setText(bundle.getString("animalDescription"));
            animalImageView.setImageResource(bundle.getInt("animalImageResource"));
        }

        navigateToHomeButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_animalDetailFragment_to_homeFragment);  // Use the action from the nav graph
        });

        return rootView;
    }
}
