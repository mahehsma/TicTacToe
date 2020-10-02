package com.example.tictactoe.game;

import android.widget.Toast;

import java.util.ArrayList;

public class Minimax {
    private char figurePlayer;
    private char figurePc;
    private float randomness;

    public Minimax(Difficulty difficulty, char figurePlayer, char figurePc) {
        this.figurePlayer = figurePlayer;
        this.figurePc = figurePc;
        setRandomness(difficulty);
    }

    private void setRandomness(Difficulty difficulty) {
        switch (difficulty) {
            case Easy:
                this.randomness = 0.5f;
                break;
            case Hard:
                this.randomness = 0.3f;
                break;
            default:
                this.randomness = 0;
        }
    }

    private int getEmptyField(Board board) {
        int emptyField = -1;
        for (int i = 0; i < board.getBoard().length; i++) {
            if (board.isEmpty(i)) {
                emptyField = i;
                break;
            }
        }
        return emptyField;
    }

    private int randomMove(Board board) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (board.isEmpty(i)) possibleMoves.add(i);
        }
        return possibleMoves.get((int) (Math.random() * possibleMoves.size()));
    }

    public int getBestMove(Board board) {
        Board clonedBoard = board.cloneBoard();
        int bestMove = getEmptyField(clonedBoard);
        int value;
        int bestValue = Integer.MIN_VALUE;
        for (int i = 0; i < 9; i++) {
            if (clonedBoard.isEmpty(i)) {
                clonedBoard.placeFigure(i, figurePc);
                value = minimax(clonedBoard, false, 0);
                clonedBoard.placeFigure(i, ' ');
                if (value >= bestValue) {
                    bestValue = value;
                    bestMove = i;
                }
            }
        }
        if (Math.random() < randomness) {
            return randomMove(clonedBoard);
        }
        return bestMove;
    }

    private int minimax(Board board, boolean isMaximizing, int depth) {
        int value = 0;
        if (board.isDraw()) return 0;
        if (isMaximizing) {
            if (board.hasWon()) return Integer.MIN_VALUE;
            int bestValue = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board.isEmpty(i)) {
                    board.placeFigure(i, figurePc);
                    value = minimax(board, false, depth + 1);
                    board.placeFigure(i, ' ');
                    bestValue = Math.max(bestValue, value);
                }
            }
            return Math.min(bestValue - depth, bestValue);
        } else{
            if(board.hasWon()) return Integer.MAX_VALUE;
            int bestValue = Integer.MAX_VALUE;
            for(int i = 0; i < 9; i++) {
                if (board.isEmpty(i)) {
                    board.placeFigure(i, figurePlayer);
                    value = minimax(board, true, depth + 1);
                    board.placeFigure(i, ' ');
                    bestValue = Math.min(bestValue, value);
                }
            }
            return Math.max(bestValue + depth, bestValue);
        }
    }
}
