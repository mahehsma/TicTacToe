package com.example.tictactoe.ui;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoe.R;
import com.example.tictactoe.game.Game;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogFragment_GameOver#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragment_GameOver extends DialogFragment implements View.OnClickListener {

    private String endMessage;

    public DialogFragment_GameOver() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment_GameOver.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogFragment_GameOver newInstance(String endMessage) {
        DialogFragment_GameOver fragment = new DialogFragment_GameOver();
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
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, Fragment_GameBoard.newInstance()).commit();
        this.dismiss();
    }
}