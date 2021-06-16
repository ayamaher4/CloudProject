package Fragments;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.cloudproject.R;

import DataBase.DBHelper;

public class SignInFragment extends Fragment {


    private final String FILE_NAME = "Signin_details";

    private final String KEY_EMAIL = "EMAIL";
    private final String KEY_PASSWORD = "PAS";

    private final String VALID_USER1 = "Aya";
    private final String VALID_PASS1 = "123";

    private final String VALID_USER2 = "Rana";
    private final String VALID_PASS2 = "456";

    EditText Email ,Password;
    Button SignIn;
    DBHelper DB;
    //DB = new DBHelper(getContext());

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_sign_in, container, false);
        Email = root.findViewById(R.id.login_Email);
        Password = root.findViewById(R.id.login_password);
        SignIn = root.findViewById(R.id.btnSignIn);
        SharedPreferences SP = getActivity().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.clContainer, homeFragment);
        ft.addToBackStack(null);

        if(SP.contains(KEY_EMAIL) && SP.contains(KEY_PASSWORD)){
            ft.commit();
        }



        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = SP.edit();
                String email = Email.getText().toString();
                String password = Password.getText().toString();




                if ( ( email.equals(VALID_USER1) && password.equals(VALID_PASS1)  )
                        || ( email.equals(VALID_USER2) && password.equals(VALID_PASS2) ) ) {
                    editor.putString(KEY_EMAIL, email );
                    editor.putString(KEY_PASSWORD, password);


                    if(editor.commit()){
                        Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                        ft.commit();
                    }
                    else{
                        Toast.makeText(getActivity(), "Failed to Add", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Credentials are not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;

    }
}