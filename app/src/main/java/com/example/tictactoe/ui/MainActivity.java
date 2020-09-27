package com.example.tictactoe.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tictactoe.R;
import com.example.tictactoe.game.Game;
import com.example.tictactoe.game.Winner;
import com.example.tictactoe.ui.Fragment_GameBoard;
import com.example.tictactoe.ui.Fragment_GameOver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment_Game = new Fragment_GameBoard();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment_Game);
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

    private void showSettings() {
        Activity settingsActivity = new SettingsActivity();
        Intent intent = new Intent(this, settingsActivity.getClass());
        this.startActivity(intent);
    }

    private void showCredits() {
        Activity creditsActivity = new CreditsActivity();
        Intent intent = new Intent(this, creditsActivity.getClass());
        this.startActivity(intent);
    }
}