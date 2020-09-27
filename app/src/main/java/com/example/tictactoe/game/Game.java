package com.example.tictactoe.game;


import android.app.Activity;
import android.widget.Button;
import android.widget.Toast;



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
        minimax = new Minimax(figurePlayer,figurePc);
        board = new Board();
        gameOver = false;
        for(Button button : buttons){
            button.setText(" ");
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
            buttons[position].setText(figurePlayer+" ");
            if(board.hasWon()){
                setGameOver(Winner.PLAYER);
            }
            if(!board.isDraw() && !gameOver) {
                int enemyMove = minimax.getBestMove(this.board);
                this.board.placeFigure(enemyMove, figurePc);
                buttons[enemyMove].setText(figurePc+" ");
                if(board.hasWon()) setGameOver(Winner.AI);
            } else
                setGameOver(Winner.DRAW);
        }
    }

    private boolean hasEnded(char figure){
        if(board.hasWon()){
            print(figure + " has won!");
            return true;
        }
        if(board.isDraw()){
            print("Draw!");
            return true;
        }
        return false;
    }

    private void print(String message){
        //Toast.makeText(this.getApplicationContext(),message, Toast.LENGTH_SHORT).show();
        buttons[0].setText(message);
    }

}
