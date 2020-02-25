package Logic;

import GUI.GUI;

import java.util.ArrayList;
import java.util.Scanner;

public class GameFlow {

    public void startUp() {
        GUI gui = new GUI();
        gui.intro();
    }

    public void runTheGame() {
        // Initialize Board
        Board board = new Board();
        // Initialize GUI
        GUI gui = new GUI();
        // Initialize Game Logic
        GameLogic logic = new GameLogic();
        // Initialize Scanner
        Scanner read = new Scanner(System.in);
        // Create ArrayList with allowed moves
        ArrayList<String> allowedMoves = gui.moves();
        // Initialize Game
        GameFlow game = new GameFlow();
        // Game Turn Counter - Ties into turn and winning
        int counter = 0;

        // As long as no winning condition is met it runs
        while(logic.gameWon(game, board, counter, read) == false) {
            // Figure out whose turn it is.
            if (counter%2 == 0) {
                board.isXTurn = true;
            } else {
                board.isXTurn = false;
            }

            // Array to control allowed options
            // Options Counter
            int x = 1;
            System.out.println("---------------------------");
            for (int i = 0; i < allowedMoves.size(); i++) {
                System.out.println(x + ": " + allowedMoves.get(i));
                x++;
            }
            System.out.println("---------------------------");

            // Print board on first turn
            if (counter == 0) {
                gui.printBoard(board);
            }

            // Move is made and move made removed from list
            int choice = read.nextInt();
            board.makeMove(board, allowedMoves.get(choice-1));
            System.out.println();
            gui.printBoard(board);
            System.out.println();
            allowedMoves.remove(choice-1);

            // Counter to keep track of game state.
            counter += 1;
            if (counter == 9) {
                System.out.println("Game Over - Draw!");
                replay(read);
            }

            // Print whose turn it is next.
            if (counter%2 == 0) {
                System.out.println("X's turn:");
            } else {
                System.out.println("O's turn:");
            }
        }
    }

    // Method to start the game over
    public void replay(Scanner read) {
        System.out.println("Would you like to play again?");
        System.out.println("If yes press 1. If no press 2");
        int re = read.nextInt();
        if (re == 1) {
            runTheGame();
        }
        if (re == 2) {
            System.exit(0);
        }
    }
}
