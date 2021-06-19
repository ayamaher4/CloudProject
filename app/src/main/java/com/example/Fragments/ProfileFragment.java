package com.example.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Activities.SignInActivity;
import com.example.Activities.SignUpActivity;
import com.example.cloudproject.R;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_profile, container, false);

        getActivity().setTitle("Profile");

        TextView usernameTV = root.findViewById(R.id.usernameTV);
        EditText usernameET = root.findViewById(R.id.usernameET);
        EditText emailET = root.findViewById(R.id.emailET);
        EditText passwordET = root.findViewById(R.id.passwordET);
        EditText phoneET = root.findViewById(R.id.phoneET);
        Button saveBtn = root.findViewById(R.id.saveBtn);



        SharedPreferences SP = getActivity().getSharedPreferences(SignInActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String usernameSP = SP.getString(SignInActivity.KEY_USERNAME, "");
        String emailSP = SP.getString(SignInActivity.KEY_EMAIL, "");
        String passwordSP = SP.getString(SignInActivity.KEY_PASSWORD, "");
        String phoneSP = SP.getString(SignInActivity.KEY_PHONE, "");

        usernameTV.setText(usernameSP);
        usernameET.setText(usernameSP);
        emailET.setText(emailSP);
        passwordET.setText(passwordSP);
        phoneET.setText(phoneSP);

        saveBtn.setOnClickListener(v -> {

            String userName = usernameET.getText().toString().trim();
            String email = emailET.getText().toString().trim();
            String password = passwordET.getText().toString().trim();
            String phone = phoneET.getText().toString().trim();

            if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                Toast.makeText(getActivity(), "Please make sure to fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = SP.edit();
            editor.clear();

            editor.putString(SignInActivity.KEY_USERNAME, userName)
                    .putString(SignInActivity.KEY_EMAIL, email)
                    .putString(SignInActivity.KEY_PASSWORD, password)
                    .putString(SignInActivity.KEY_PHONE, phone);
            editor.apply();

            usernameTV.setText(userName);

            Toast.makeText(getActivity(), "Update Profile Successfully", Toast.LENGTH_SHORT).show();
        });
        return root;
    }
}