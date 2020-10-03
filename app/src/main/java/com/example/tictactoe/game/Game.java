package com.example.tictactoe.game;


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;

import androidx.preference.PreferenceManager;


public class Game {
    private Button[] buttons;
    private char figurePlayer = 'X';
    private char figurePc = 'O';
    private Minimax minimax;
    private Board board;
    private boolean gameOver;
    private Winner state;
    private Difficulty difficulty;

    public Game(Button[] buttons, Difficulty difficulty) {
        this.buttons = buttons;
        this.difficulty = difficulty;
        resetGame();
    }

    private void resetGame(){
        minimax = new Minimax(this.difficulty, this.figurePlayer, this.figurePc);
        board = new Board();
        gameOver = false;
        for(Button button : buttons){
            button.setText("");
        }
    }

    public boolean isGameOver(){
        return gameOver;
    }

    private void setGameOver(Winner state){
        this.state = state;
        this.gameOver = true;
    }

    public Winner whoWon(){
        return state;
    }

    public void makeMove(int position){
        if(this.board.isEmpty(position) && !gameOver){
            this.board.placeFigure(position, figurePlayer);
            buttons[position].setText(figurePlayer + "");
            if (board.hasWon()) {
                setGameOver(Winner.PLAYER);
            } else if (!board.isDraw() && !gameOver) {
                int enemyMove = minimax.getBestMove(this.board);
                this.board.placeFigure(enemyMove, figurePc);
                buttons[enemyMove].setText(figurePc + "");
                if (board.hasWon()) setGameOver(Winner.AI);
            } else
                setGameOver(Winner.DRAW);
        }
    }
}
