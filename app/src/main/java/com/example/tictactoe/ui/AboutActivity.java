package com.example.tictactoe.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tictactoe.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadTheme();
        setContentView(R.layout.activity_about);
    }

    private void loadTheme() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = preferences.getString("list_preference_color", "yellow");
        switch (theme) {
            case "yellow":
                getTheme().applyStyle(R.style.AppOverlayYellow, true);
                break;
            case "blue":
                getTheme().applyStyle(R.style.AppOverlayBlue, true);
                break;
            case "black":
                getTheme().applyStyle(R.style.AppOverlayBlack, true);
                break;
            default:
                getTheme().applyStyle(R.style.AppOverlayRed, true);
                break;
        }
    }
}