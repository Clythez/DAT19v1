package Logic;

import java.util.Scanner;

public class Board {

    // Double Array to represent board
    private State[][] board = new State[3][3];
    // Field to control turn
    public boolean isXTurn = true;

    // Create board when constructor is called
    public Board() {
        createBoard();
    }

    // Create board - Double for loop
    private void createBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = State.E;
            }
        }
    }

    // Switch case on string to alter the state of selection
    public void makeMove(Board board, String choice) {
        switch (choice) {
            case "Top Left":
                board.board[0][0] = isXTurn ? State.X : State.O;
                break;
            case "Top Middle":
                board.board[0][1] = isXTurn ? State.X : State.O;
                break;
            case "Top Right":
                board.board[0][2] = isXTurn ? State.X : State.O;
                break;
            case "Middle Left":
                board.board[1][0] = isXTurn ? State.X : State.O;
                break;
            case "Middle":
                board.board[1][1] = isXTurn ? State.X : State.O;
                break;
            case "Middle Right":
                board.board[1][2] = isXTurn ? State.X : State.O;
                break;
            case "Bottom Left":
                board.board[2][0] = isXTurn ? State.X : State.O;
                break;
            case "Bottom Middle":
                board.board[2][1] = isXTurn ? State.X : State.O;
                break;
            case "Bottom Right":
                board.board[2][2] = isXTurn ? State.X : State.O;
                break;


        }
    }

    // Method to get the current board
    public State[][] getBoard() {
        return board;
    }
}

/* Unused methods:

    public State getState(Board board, int row, int col) {
        return board.board[row][col];
    }

    public State getCurrentUser() {
        return isXTurn ? State.X : State.O;
    }
 */