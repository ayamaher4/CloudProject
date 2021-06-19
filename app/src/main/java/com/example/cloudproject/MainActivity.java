package com.example.cloudproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.Activities.SignInActivity;
import com.example.Fragments.AboutFragment;
import com.example.Fragments.HomeFragment;
import com.example.Fragments.ProfileFragment;
import com.example.Fragments.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        openFragment(new HomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        openFragment(new HomeFragment());
                        return true;
                    case R.id.navigation_Videos:
                        openFragment(new VideoFragment());
                        return true;
                  /*  case R.id.navigation_About:
                        openFragment(new AboutFragment());
                        return true;*/
                    case R.id.navigation_Profile:
                        openFragment(new ProfileFragment());
                        return true;
                }
                return false;
            }
        });
    }

    public void openFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
//        HomeFragment homeFragment = new HomeFragment();
//        HomeFragment homeFragment = new HomeFragment();
        ft.replace(R.id.clContainer, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}