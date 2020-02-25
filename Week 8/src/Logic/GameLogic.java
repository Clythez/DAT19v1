package Logic;

import java.util.Scanner;

public class GameLogic {

    // Umbrella method evaluating all conditions every turn
    public boolean gameWon (GameFlow game, Board board, int counter, Scanner read) {
        boolean flag = false;
        if (threeOver(board) || threeUp(board) || diagonal(board)) {
            flag = true;
        }
        if (flag) {
            if (counter%2 == 0) {
                System.out.println("--------------------");
                System.out.println("| O is the WINNER! |");
                System.out.println("| O is the WINNER! |");
                System.out.println("| O is the WINNER! |");
                System.out.println("| O is the WINNER! |");
                System.out.println("| O is the WINNER! |");
                System.out.println("--------------------");
            } else {
                System.out.println("--------------------");
                System.out.println("| X is the WINNER! |");
                System.out.println("| X is the WINNER! |");
                System.out.println("| X is the WINNER! |");
                System.out.println("| X is the WINNER! |");
                System.out.println("| X is the WINNER! |");
                System.out.println("--------------------");
            }
            game.replay(read);
        }
        return flag;
    }

    // Checks for 3 across in all 3 rows
    public boolean threeOver(Board board) {
        boolean flag = false;
        State[][] currentBoard = board.getBoard();
        if (!(currentBoard[0][0] == State.E)) {
            if (currentBoard[0][0] == currentBoard[0][1] && currentBoard[0][0] == currentBoard[0][2]) {
                flag = true;
            }
        if (!(currentBoard[1][0] == State.E)) {
            if (currentBoard[1][0] == currentBoard[1][1] && currentBoard[1][0] == currentBoard[1][2]) {
                flag = true;
            }
        }
        if (!(currentBoard[2][0] == State.E))
            if (currentBoard[2][0] == currentBoard[2][1] && currentBoard[2][0] == currentBoard[2][2]) {
                flag = true;
            }
        }
        return flag;
    }

    // Checks for 3 down in all 3 columns
    public boolean threeUp(Board board) {
        boolean flag = false;
        State[][] currentBoard = board.getBoard();
        if (!(currentBoard[0][0] == State.E)) {
            if (currentBoard[0][0] == currentBoard[1][0] && currentBoard[0][0] == currentBoard[2][0]) {
                flag = true;
            }
        if (!(currentBoard[0][1] == State.E))
            if (currentBoard[0][1] == currentBoard[1][1] && currentBoard[0][1] == currentBoard[2][1]) {
                flag = true;
            }
        if (!(currentBoard[0][2] == State.E))
            if (currentBoard[0][2] == currentBoard[1][2] && currentBoard[0][2] == currentBoard[2][2]) {
                flag = true;
            }
        }
        return flag;
    }

    // Checks for diagonal win condition
    public boolean diagonal(Board board) {
        boolean flag = false;
        State[][] currentBoard = board.getBoard();
        if (!(currentBoard[1][1] == State.E)) {
            if (currentBoard[0][0] == currentBoard[1][1] && currentBoard[0][0] == currentBoard[2][2]) {
                flag = true;
            }
            if (currentBoard[0][2] == currentBoard[1][1] && currentBoard[0][2] == currentBoard[2][0]) {
                flag = true;
            }
        }
        return flag;
    }
}
