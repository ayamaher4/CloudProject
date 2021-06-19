<<<<<<< HEAD:app/src/main/java/com/example/cloudproject/SettingsFragments.java
package com.example.cloudproject;
=======
package com.example.Fragments;
>>>>>>> 03d48e7a95b86a1bd9fac90a751436c0b32eb3a5:app/src/main/java/com/example/Fragments/SettingsFragments.java

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragments extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener  {

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(getString(R.string.key_font_size))) {
            getActivity().setResult(Activity.RESULT_OK);
        } else {
            boolean isNight = sharedPreferences.getBoolean(getString(R.string.key_night_mode), false);
            if (isNight) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }
    }


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
    }


    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();

    }
}