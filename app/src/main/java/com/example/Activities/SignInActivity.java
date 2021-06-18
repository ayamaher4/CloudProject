package com.example.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cloudproject.MainActivity;
import com.example.cloudproject.R;

import com.example.DataBase.DBHelper;

public class SignInActivity extends AppCompatActivity {


    public final static String SHARED_PREFERENCES_NAME = "CloudProject";
    public final static String KEY_USERNAME = "USERNAME";
    public final static String KEY_EMAIL = "EMAIL";
    public final static String KEY_PASSWORD = "PASSWORD";
    public final static String KEY_PHONE = "PHONE";

    EditText UserNameEmail, Password;
    Button SignIn;
    TextView signup;
    DBHelper DB;
    //DB = new DBHelper(getContext());

    public SignInActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        UserNameEmail = findViewById(R.id.login_UserName_Email);
        Password = findViewById(R.id.login_password);
        SignIn = findViewById(R.id.btnSignIn);
        signup = findViewById(R.id.signup);
        SharedPreferences SP = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);


        signup.setOnClickListener((view) -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
        });

        SignIn.setOnClickListener(v -> {
            String userNameEmail = UserNameEmail.getText().toString().trim();
            String password = Password.getText().toString().trim();

            String emailSP = SP.getString(KEY_EMAIL, "");
            String userNameEmailSP = SP.getString(KEY_USERNAME, "");
            String passwordSP = SP.getString(KEY_PASSWORD, "");

            if (userNameEmail.isEmpty() || password.isEmpty()) {
                Toast.makeText(SignInActivity.this, "Please write your email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            if ((userNameEmail.equalsIgnoreCase(emailSP) && password.equals(passwordSP)) || (userNameEmail.equalsIgnoreCase(userNameEmailSP) && password.equals(passwordSP))) {
                Toast.makeText(SignInActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(SignInActivity.this, "Credentials are not valid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}