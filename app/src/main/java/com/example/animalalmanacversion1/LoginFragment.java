package com.example.animalalmanacversion1;

import android.content.Context;
import android.content.SharedPreferences;
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
    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize views
        EditText usernameEditText = view.findViewById(R.id.username);
        EditText passwordEditText = view.findViewById(R.id.password);
        Button submitButton = view.findViewById(R.id.submit);
        Button signUpButton = view.findViewById(R.id.sign_up);

        // Set up button listeners
        submitButton.setOnClickListener(v -> handleLogin(usernameEditText, passwordEditText, v));

        signUpButton.setOnClickListener(v -> {
            try {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_loginFragment_to_signUpFragment);
            } catch (Exception e) {
                Log.e(TAG, "Navigation failed: " + e.getMessage());
            }
        });

        return view;
    }

    private void handleLogin(EditText usernameEditText, EditText passwordEditText, View view) {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve stored credentials from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString(KEY_USERNAME, null);
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, null);

        // Validate credentials
        if (savedUsername != null && savedPassword != null) {
            if (username.equals(savedUsername) && password.equals(savedPassword)) {
                Toast.makeText(getActivity(), "Login Successful!", Toast.LENGTH_SHORT).show();
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.homeFragment);
            } else {
                Toast.makeText(getActivity(), "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "No saved credentials found", Toast.LENGTH_SHORT).show();
        }
    }
}
