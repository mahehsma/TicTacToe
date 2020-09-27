package com.example.tictactoe.game;

public class Board {
    private char[] board = new char[9];

    public Board(){
        initialize();
    }

    private void initialize(){
        for(int i = 0; i < board.length; i++){
            board[i]=' ';
        }
    }

    public void placeFigure(int position, char figure) {
        this.board[position] = figure;
    }

    public boolean isEmpty(int position){
        return this.board[position] == ' ';
    }


    public boolean hasWon(){
        if((board[0] == board[1] && board[1] == board[2] && board[2] != ' ')||
                (board[3] == board[4] && board[4] == board[5] && board[5] != ' ')||
                (board[6] == board[7] && board[7] == board[8] && board[8] != ' ')||

                (board[0] == board[3] && board[3] == board[6] && board[6] != ' ')||
                (board[1] == board[4] && board[4] == board[7] && board[7] != ' ')||
                (board[2] == board[5] && board[5] == board[8] && board[8] != ' ')||

                (board[0] == board[4] && board[4] == board[8] && board[8] != ' ')||
                (board[2] == board[4] && board[4] == board[6] && board[6] != ' ')) {
            return true;
        }
        return false;
    }

    public boolean isDraw(){
       return !this.hasWon() && this.isFull();
    }

    private boolean isFull(){
        for(char field : board){
            if(field == ' ') return false;
        }
        return true;
    }

    public char[] getBoard(){ return this.board;}

    public Board cloneBoard(){
        Board clonedBoard = new Board();
        for(int i = 0; i < board.length; i++){
            clonedBoard.placeFigure(i,board[i]);
        }
        return clonedBoard;
    }
}
