package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tictactoe.game.Game;
import com.example.tictactoe.ui.Fragment_GameBoard;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttons[] = new Button[9];
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment_Game = new Fragment_GameBoard();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment_Game);
        initialize();
    }

    private void initialize(){
        buttons[0] = findViewById(R.id.buttonTL);
        buttons[1] = findViewById(R.id.buttonTM);
        buttons[2] = findViewById(R.id.buttonTR);
        buttons[3] = findViewById(R.id.buttonML);
        buttons[4] = findViewById(R.id.buttonMM);
        buttons[5] = findViewById(R.id.buttonMR);
        buttons[6] = findViewById(R.id.buttonBL);
        buttons[7] = findViewById(R.id.buttonBM);
        buttons[8] = findViewById(R.id.buttonBR);
        for(Button button : buttons){
            button.setOnClickListener(this);
        }
        game = new Game(buttons);
    }

    @Override
    public void onClick(View v) {
        if(game.isGameOver()){
            Toast.makeText(getApplicationContext(), "GameOver", Toast.LENGTH_SHORT).show();
            Fragment fragment_GameOver = new Fragment_GameOver();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, fragment_GameOver).commit();
        } else{
        switch (v.getId()) {
            case R.id.buttonTL:
                game.makeMove(0);
                break;
            case R.id.buttonTM:
                game.makeMove(1);
                break;
            case R.id.buttonTR:
                game.makeMove(2);
                break;
            case R.id.buttonML:
                game.makeMove(3);
                break;
            case R.id.buttonMM:
                game.makeMove(4);
                break;
            case R.id.buttonMR:
                game.makeMove(5);
                break;
            case R.id.buttonBL:
                game.makeMove(6);
                break;
            case R.id.buttonBM:
                game.makeMove(7);
                break;
            case R.id.buttonBR:
                game.makeMove(8);
                break;
        }
        }
    }


    private void print(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}