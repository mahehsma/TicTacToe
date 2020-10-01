package com.example.tictactoe.game;

import android.widget.Toast;

public class Minimax {
    private char figurePlayer;
    private char figurePc;
    private Difficulty difficulty;

    public Minimax(char figurePlayer, char figurePc){
        this.figurePlayer = figurePlayer;
        this.figurePc = figurePc;
    }

    private int getEmptyField(Board board){
        int emptyField = 0;
        for(int i = 0; i < board.getBoard().length; i++){
            if(board.isEmpty(i)){
                emptyField = i;
                break;
            }
        }
        return emptyField;
    }

    public int getBestMove(Board board){
        Board clonedBoard = board.cloneBoard();
        int bestMove = getEmptyField(clonedBoard);
        int value;
        int bestValue = Integer.MIN_VALUE;
        for(int i = 0; i < 9; i++){
            if(clonedBoard.isEmpty(i)) {
                clonedBoard.placeFigure(i,figurePc);
                value = minimax(clonedBoard, false);
                clonedBoard.placeFigure(i,' ');
                if(value >= bestValue){
                    bestValue = value;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }
    private int minimax(Board board, boolean isMaximizing){
        int value=0;
        if(board.isDraw()) return 0;
        if(isMaximizing){
            if(board.hasWon()) return Integer.MIN_VALUE;
            int bestValue = Integer.MIN_VALUE;
            for(int i = 0; i < 9; i++) {
                if (board.isEmpty(i)) {
                    board.placeFigure(i, figurePc);
                    value = minimax(board, false);
                    board.placeFigure(i, ' ');
                    bestValue = Math.max(bestValue, value);
                }
            }
            return bestValue;
        } else{
            if(board.hasWon()) return Integer.MAX_VALUE;
            int bestValue = Integer.MAX_VALUE;
            for(int i = 0; i < 9; i++) {
                if (board.isEmpty(i)) {
                    board.placeFigure(i, figurePlayer);
                    value = minimax(board, true);
                    board.placeFigure(i, ' ');
                    bestValue = Math.min(bestValue, value);
                }
            }
            return bestValue;
        }
    }
}
