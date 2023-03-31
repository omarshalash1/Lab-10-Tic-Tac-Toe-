import java.util.Scanner;

public class TicTacToe {
    // Set constants for the number of rows and columns on the game board
    private static final int ROW = 3;
    private static final int COL = 3;
    // Create a 2D array to represent the game board
    private static String[][] board = new String[ROW][COL];
    // Set the starting player to be X
    private static String player = "X";

    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner console = new Scanner(System.in);

        // Initialize the game board
        clearBoard();

        // Run the game loop
        while (true) {
            // Display the current state of the game board
            display();

            // Get the player's move
            int rowMove;
            int colMove;
            do {
                // Prompt the user to enter a row and column for their move
                rowMove = getValidMove(console, "row");
                colMove = getValidMove(console, "column");
            } while (!isValidMove(rowMove, colMove));

            // Update the game board with the player's move
            board[rowMove - 1][colMove - 1] = player;

            // Check if the current player has won
            if (isColWin(player) || isDiagonalWin(player)) {
                // Display the winning board configuration and message
                display();
                System.out.println("Player " + player + " wins!");
                // Ask the player if they want to play again
                if (playAgain(console)) {
                    // If yes, reset the game board and continue the loop
                    clearBoard();
                    continue;
                } else {
                    // If no, break out of the loop and end the game
                    break;
                }
            } else if (isTie()) {
                // Check if the game is a tie
                // Display the current board configuration and message
                display();
                System.out.println("Tie game!");
                // Ask the player if they want to play again
                if (playAgain(console)) {
                    // If yes, reset the game board and continue the loop
                    clearBoard();
                    continue;
                } else {
                    // If no, break out of the loop and end the game
                    break;
                }
            }

            // Switch to the other player's turn
            player = (player.equals("X")) ? "O" : "X";
        }

        // Close the Scanner object
        console.close();
    }

    // Check if the game is a tie
    private static boolean isTie() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Ask the player if they want to play again
    private static boolean playAgain(Scanner console) {
        String input;
        do {
            System.out.print("Play again? (y/n): ");
            input = console.nextLine().trim().toLowerCase();
        } while (!input.equals("y") && !input.equals("n"));
        return input.equals("y");
    }

    // Initialize the game board with empty spaces
    private static void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row                ][col] = " ";
            }
        }
    }

    // Display the current state of the game board
    private static void display() {
        System.out.println();
        System.out.println("  1 2 3");
        for (int row = 0; row < ROW; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < COL; col++) {
                System.out.print(board[row][col]);
                if (col < COL - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < ROW - 1) {
                System.out.println("  -----");
            }
        }
        System.out.println();
    }

    // Prompt the user to enter a valid move for a given coordinate (row or column)
    private static int getValidMove(Scanner console, String coordinate) {
        return SafeInput.getRangedInt(console, "Enter " + coordinate + " (1-3): ", 1, 3);
    }

    // Check if a given move is valid (i.e. the space is not already occupied)
    private static boolean isValidMove(int row, int col) {
        if (!board[row - 1][col - 1].equals(" ")) {
            System.out.println("Error: That space is already occupied.");
            return false;
        }
        return true;
    }

    // Check if the current player has won in a column
    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Check if the current player has won on a diagonal
    private static boolean isDiagonalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }
}
