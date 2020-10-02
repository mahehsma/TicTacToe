package com.example.tictactoe.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tictactoe.R;
import com.example.tictactoe.Settings;
import com.example.tictactoe.game.Difficulty;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fragment fragment_Game = new Fragment_GameBoard();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment_Game);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);
        loadPreferences();
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                showSettings();
                return true;
            case R.id.credits:
                showCredits();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        switch (preferences.getString("list_preference_difficulty", "Impossible")) {
            case "Easy":
                Settings.difficulty = Difficulty.Easy;
                break;
            case "Hard":
                Settings.difficulty = Difficulty.Hard;
                break;
            default:
                Settings.difficulty = Difficulty.Impossible;
        }
        String preferencesColor = preferences.getString("list_preference_color", "yellow");
        setAppColor(preferencesColor);
        if (Settings.appColor != null) {
            if (Settings.appColor != preferencesColor) {
                Settings.appColor = preferencesColor;
                this.recreate();
            }
        } else {
            Settings.appColor = preferencesColor;
        }
    }

    private void setAppColor(String appColor) {
        switch (appColor) {
            case "yellow":
                getTheme().applyStyle(R.style.AppOverlayYellow, true);
                break;
            case "blue":
                getTheme().applyStyle(R.style.AppOverlayBlue, true);
                break;
            default:
                getTheme().applyStyle(R.style.AppOverlayRed, true);
                break;
        }
    }

    private void showSettings() {
        Activity settingsActivity = new SettingsActivity();
        this.startActivity(new Intent(this, settingsActivity.getClass()));
    }

    private void showCredits() {
        Activity creditsActivity = new AboutActivity();
        this.startActivity(new Intent(this, creditsActivity.getClass()));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Toast.makeText(getApplicationContext(), "settings changed", Toast.LENGTH_SHORT).show();
        loadPreferences();
    }
}