package com.example.tictactoe.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.tictactoe.R;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private String appTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);
        loadTheme(preferences.getString("list_preference_color", "yellow"));

        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

        this.getSupportActionBar().setTitle("Settings");
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (appTheme != sharedPreferences.getString("list_preference_color", null)) {
            appTheme = sharedPreferences.getString("list_preference_color", null);
            MainActivity.hasToRecreate = true;
            this.recreate();

        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

    private void loadTheme(String appTheme) {
        this.appTheme = appTheme;
        switch (appTheme) {
            case "yellow":
                setTheme(R.style.AppOverlayYellow);
                break;
            case "blue":
                setTheme(R.style.AppOverlayBlue);
                break;
            case "black":
                setTheme(R.style.AppOverlayBlack);
                break;
            default:
                setTheme(R.style.AppOverlayRed);
                break;
        }
    }
}