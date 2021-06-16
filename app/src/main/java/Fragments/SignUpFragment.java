package Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cloudproject.R;


public class SignUpFragment extends Fragment {

    private final String FILE_NAME = "signup_details";
    private final String KEY_NAME = "NAME";
    private final String KEY_EMAIL = "EMAIL";
    private final String KEY_PASSWORD = "PASSWORD";
    private final String KEY_PHONE = "PHONE";


    EditText Name,Email ,Password ,Phone;
    Button SignUp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_sign_in, container, false);
        Name = root.findViewById(R.id.usernameInput);
        Email = root.findViewById(R.id.emailInput);
        Password = root.findViewById(R.id.passwordInput);
        Phone = root.findViewById(R.id.phoneInput);
        SignUp = root.findViewById(R.id.btnSignUp);

        SharedPreferences SP = getActivity().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        Name.setText("User Name: " + SP.getString(KEY_NAME, ""));
        Email.setText("User Name: " + SP.getString(KEY_EMAIL, ""));
        Password.setText("Password: " + SP.getString(KEY_PASSWORD, ""));
        Phone.setText("User Name: " + SP.getString(KEY_PHONE, ""));

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = SP.edit();
                editor.clear();
                editor.commit();

                SignInFragment loginFragment = new SignInFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.clContainer, loginFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return root;
    }
}


