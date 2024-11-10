package com.example.animalalmanacversion1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "LoginFragment loaded");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize views
        EditText usernameEditText = view.findViewById(R.id.username);
        EditText passwordEditText = view.findViewById(R.id.password);
        Button submitButton = view.findViewById(R.id.submit);
        Button signUpButton = view.findViewById(R.id.sign_up);

        // Log to confirm views were found
        Log.d(TAG, "Views initialized");

        // Set up button listeners
        submitButton.setOnClickListener(v -> {
            Log.d(TAG, "Submit button clicked!");

            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Login Successful!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Login Successful");
            }
        });

        signUpButton.setOnClickListener(v -> {
            Log.d(TAG, "Sign-Up button clicked!");

            try {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_loginFragment_to_signUpFragment);
                Log.d(TAG, "Navigating to SignUpFragment");
            } catch (Exception e) {
                Log.e(TAG, "Navigation failed: " + e.getMessage());
            }
        });

        Log.d(TAG, "Submit Button - Enabled: " + submitButton.isEnabled() + ", Clickable: " + submitButton.isClickable());
        Log.d(TAG, "Sign-Up Button - Enabled: " + signUpButton.isEnabled() + ", Clickable: " + signUpButton.isClickable());

        return view;
    }
}
