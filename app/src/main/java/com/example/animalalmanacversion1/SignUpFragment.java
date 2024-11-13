package com.example.animalalmanacversion1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignUpFragment extends Fragment {

    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        // Initialize views
        EditText usernameEditText = view.findViewById(R.id.username);
        EditText passwordEditText = view.findViewById(R.id.password);
        EditText confirmPasswordEditText = view.findViewById(R.id.confirm_password);
        Button createAccountButton = view.findViewById(R.id.create_account_button);
        Button backToLoginButton = view.findViewById(R.id.back_to_login_button);

        // Set up the Create Account button click listener
        createAccountButton.setOnClickListener(v -> handleSignUp(usernameEditText, passwordEditText, confirmPasswordEditText, v));

        // Set up the Back to Login button click listener
        backToLoginButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.loginFragment);
        });

        return view;
    }

    private void handleSignUp(EditText usernameEditText, EditText passwordEditText, EditText confirmPasswordEditText, View view) {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Validate the input fields
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getActivity(), "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(getActivity(), "Please re-enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save username and password to SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();

        Toast.makeText(getActivity(), "Account created successfully!", Toast.LENGTH_SHORT).show();

        // Navigate back to LoginFragment
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.loginFragment);
    }
}
