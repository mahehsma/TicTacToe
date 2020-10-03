package com.example.tictactoe.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tictactoe.R;
import com.example.tictactoe.game.Difficulty;
import com.example.tictactoe.game.Game;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_GameBoard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_GameBoard extends Fragment implements View.OnClickListener {
    Game game;
    Button[] buttons = new Button[9];

    public Fragment_GameBoard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment_Game.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_GameBoard newInstance() {
        Fragment_GameBoard fragment = new Fragment_GameBoard();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__game, container, false);
        initialize(v);
        return v;
    }

    private void initialize(View v){

        buttons[0] = v.findViewById(R.id.buttonTL);
        buttons[1] = v.findViewById(R.id.buttonTM);
        buttons[2] = v.findViewById(R.id.buttonTR);
        buttons[3] = v.findViewById(R.id.buttonML);
        buttons[4] = v.findViewById(R.id.buttonMM);
        buttons[5] = v.findViewById(R.id.buttonMR);
        buttons[6] = v.findViewById(R.id.buttonBL);
        buttons[7] = v.findViewById(R.id.buttonBM);
        buttons[8] = v.findViewById(R.id.buttonBR);
        for (Button button : buttons) {
            button.setOnClickListener(this);
        }
        game = new Game(buttons, getDifficulty());
    }

    private Difficulty getDifficulty() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        switch (preferences.getString("list_preference_difficulty", "Easy")) {
            case "Easy":
                return Difficulty.Easy;
            case "Hard":
                return Difficulty.Hard;
            default:
                return Difficulty.Impossible;
        }
    }


    private void showGameOver() {
        String message = getEndMessage();
        DialogFragment fragment_GameOver = DialogFragment_GameOver.newInstance(message);
        fragment_GameOver.show(getFragmentManager(), "dialog");
    }

    private String getEndMessage() {
        String message="";
        switch(game.whoWon()){
            case DRAW: message = getString(R.string.draw);
                break;
            case PLAYER: message = getString(R.string.playerWon);
                break;
            case AI: message = getString(R.string.aiWon);
                break;
        }
        return message;
    }
    @Override
    public void onClick(View v) {
        if(game.isGameOver()){
            showGameOver();
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
            if(game.isGameOver()){
                showGameOver();
            }
        }
    }
}