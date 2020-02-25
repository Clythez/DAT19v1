package GUI;

import Logic.Board;
import Logic.State;

import java.util.ArrayList;

public class GUI {

    // Method to print the board
    public void printBoard(Board boardInstance) {
        State[][] board = boardInstance.getBoard();

        for (int i = 0; i < board.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j <board[i].length; j++) {
                System.out.print(board[i][j]);
                System.out.print(" | ");
            }
            System.out.println("");
        }
    }

    // Post the introduction
    public void intro() {
        System.out.println("Hello and welcome to TicTacToe");
        System.out.println("You use the numbers 1-9 to navigate the game");
        System.out.println("");
        System.out.println("Rules are as follows:");
        System.out.println("1: X always starts");
        System.out.println("2: You win by getting 3 in a row. Up down or diagonal");
        System.out.println("");
        System.out.println("First turn:");
        System.out.println("");
    }

    // Create ArrayList with options
    public ArrayList<String> moves() {

        ArrayList<String> potentialMoves = new ArrayList<>();

        potentialMoves.add("Top Left");
        potentialMoves.add("Top Middle");
        potentialMoves.add("Top Right");
        potentialMoves.add("Middle Left");
        potentialMoves.add("Middle");
        potentialMoves.add("Middle Right");
        potentialMoves.add("Bottom Left");
        potentialMoves.add("Bottom Middle");
        potentialMoves.add("Bottom Right");

        return potentialMoves;
    }
}
