package com.example.tictactoe.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
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
}