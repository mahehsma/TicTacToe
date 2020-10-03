package com.example.tictactoe.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tictactoe.R;

public class MainActivity extends AppCompatActivity {
    public static boolean hasToRecreate = false;
    private boolean firstStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.fragmentContainer, Fragment_GameBoard.newInstance());

        loadTheme();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (hasToRecreate) {
            hasToRecreate = false;
            this.recreate();
        }
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


    private void loadTheme() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = preferences.getString("list_preference_color", "yellow");
        switch (theme) {
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

    private void showSettings() {
        Activity settingsActivity = new SettingsActivity();
        this.startActivity(new Intent(this, settingsActivity.getClass()));
    }

    private void showCredits() {
        Activity creditsActivity = new AboutActivity();
        this.startActivity(new Intent(this, creditsActivity.getClass()));
    }

}