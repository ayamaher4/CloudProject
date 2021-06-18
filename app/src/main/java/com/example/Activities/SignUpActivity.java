package com.example.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cloudproject.R;


public class SignUpActivity extends AppCompatActivity {

    EditText Username, Email, Password, Phone;
    Button SignUp;
    TextView signIn;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Username = findViewById(R.id.usernameInput);
        Email = findViewById(R.id.emailInput);
        Password = findViewById(R.id.passwordInput);
        Phone = findViewById(R.id.phoneInput);
        SignUp = findViewById(R.id.btnSignUp);
        signIn = findViewById(R.id.signIn);

        SharedPreferences SP = getSharedPreferences(SignInActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        signIn.setOnClickListener(v -> finish());

        SignUp.setOnClickListener(v -> {
            String userName = Username.getText().toString().trim();
            String email = Email.getText().toString().trim();
            String password = Password.getText().toString().trim();
            String phone = Phone.getText().toString().trim();

            if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {

                Toast.makeText(SignUpActivity.this, "Please make sure to fill in all fields", Toast.LENGTH_SHORT).show();

                return;
            }


            SharedPreferences.Editor editor = SP.edit();
            editor.clear();


            editor.putString(SignInActivity.KEY_USERNAME, userName)
                    .putString(SignInActivity.KEY_EMAIL, email)
                    .putString(SignInActivity.KEY_PASSWORD, password)
                    .putString(SignInActivity.KEY_PHONE, phone);
            editor.apply();

            Toast.makeText(SignUpActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}


