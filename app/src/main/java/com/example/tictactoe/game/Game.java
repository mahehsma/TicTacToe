package com.example.tictactoe.game;


import android.app.Activity;
import android.widget.Button;

import com.example.tictactoe.Settings;


public class Game extends Activity {
    private Button[] buttons;
    private char figurePlayer = 'X';
    private char figurePc = 'O';
    private Minimax minimax;
    private Board board;
    private boolean gameOver;
    private Winner state;

    public Game(Button[] buttons){
        this.buttons = buttons;
        resetGame();
    }

    private void resetGame(){
        minimax = new Minimax(getDifficulty(), figurePlayer, figurePc);
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

    public Difficulty getDifficulty() {
        return Settings.difficulty;
    }
}
