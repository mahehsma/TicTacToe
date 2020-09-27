package com.example.tictactoe.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoe.R;
import com.example.tictactoe.game.Game;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_GameOver#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_GameOver extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Game game;
    private Button[] buttons;
    private String endMessage;

    public Fragment_GameOver() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment Fragment_GameOver.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_GameOver newInstance(Game game, Button[] buttons, String endMessage) {
        Fragment_GameOver fragment = new Fragment_GameOver();
        Bundle args = new Bundle();
        fragment.game = game;
        fragment.buttons = buttons;
        fragment.endMessage = endMessage;
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
        View v = inflater.inflate(R.layout.fragment__game_over, container, false);
        Button button_newGame = v.findViewById(R.id.button_newGame);
        button_newGame.setOnClickListener(this);
        TextView textView = v.findViewById(R.id.textView_gameOver);
        textView.setText(endMessage);
        return v;
    }

    @Override
    public void onClick(View v) {
        game = new Game(buttons);
    }
}