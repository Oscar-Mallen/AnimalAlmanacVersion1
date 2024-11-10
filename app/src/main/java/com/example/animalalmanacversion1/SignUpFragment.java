package com.example.animalalmanacversion1;

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

public class SignUpFragment extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button createAccountButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        // Initialize views
        usernameEditText = view.findViewById(R.id.username);
        passwordEditText = view.findViewById(R.id.password);
        confirmPasswordEditText = view.findViewById(R.id.confirm_password);
        createAccountButton = view.findViewById(R.id.create_account_button);

        // Set up the Create Account button click listener
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignUp();
            }
        });

        return view;
    }

    private void handleSignUp() {
        // Get the values from the EditText fields
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

        // Perform signup logic (e.g., storing user data)
        // For demonstration purposes, we’ll just show a success message
        Toast.makeText(getActivity(), "Account created successfully!", Toast.LENGTH_SHORT).show();

        // You can add logic here to transition to another screen or go back to the login screen
    }
}
