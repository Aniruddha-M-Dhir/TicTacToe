import java.util.Scanner;

public class Main {
    public static final int element = 3; // initializing Board size (3x3)
    public char[][] playBoard;
    public char currPlayer;

    public Main() {
        playBoard = new char[element][element];
        currPlayer = 'X'; // Player X starts the game
        initializeBoard();
    }

    // Initializes the board with empty spaces
    public void initializeBoard() {
        for (int i = 0; i < element; i++) {
            for (int j = 0; j < element; j++) {
                playBoard[i][j] = ' ';
            }
        }
    }

    // Inserting a move into the board
    public boolean insertMove(int row, int col) {
        if (row < 0 || row >= element || col < 0 || col >= element || playBoard[row][col] != ' ') {
            return false; // Invalid move
        }
        playBoard[row][col] = currPlayer;
        return true;
    }

    // Displays the current board
    public void displayBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < element; i++) {
            for (int j = 0; j < element; j++) {
                System.out.print(playBoard[i][j]);
                if (j < element - 1) {
                    System.out.print("\t|\t");
                }
            }
            System.out.println();
            if (i < element - 1) {
                System.out.println("_\t_\t_\t_\t_");
            }
        }
    }

    // Checks for a winner or a tie
    public char checkWinner() {
        // Check rows and columns
        for (int i = 0; i < element; i++) {
            if (playBoard[i][0] == currPlayer && playBoard[i][1] == currPlayer && playBoard[i][2] == currPlayer) {
                return currPlayer; // Row win
            }
            if (playBoard[0][i] == currPlayer && playBoard[1][i] == currPlayer && playBoard[2][i] == currPlayer) {
                return currPlayer; // Column win
            }
        }

        // Checking diagonals
        if (playBoard[0][0] == currPlayer && playBoard[1][1] == currPlayer && playBoard[2][2] == currPlayer) {
            return currPlayer; // Main diagonal win
        }
        if (playBoard[0][2] == currPlayer && playBoard[1][1] == currPlayer && playBoard[2][0] == currPlayer) {
            return currPlayer; // Secondary diagonal win
        }

        // Check for tie
        boolean isTie = true;
        for (int i = 0; i < element; i++) {
            for (int j = 0; j < element; j++) {
                if (playBoard[i][j] == ' ') {
                    isTie = false;
                    break;
                }
            }
        }
        return isTie ? 'T' : 'N'; // T for tie, N for no winner
    }

    // Switch to the next player
    public void switchPlayer() {
        currPlayer = (currPlayer == 'X') ? 'O' : 'X';
    }

    // Main game
    public static void main(String[] args) {
        MyFavoriteApp game = new MyFavoriteApp();
        Scanner scanner = new Scanner(System.in);
        char winner = 'N';

        System.out.println("Welcome to Tic-Tac-Toe!");
        while (winner == 'N') {
            game.displayBoard();
            System.out.println("Player " + game.currPlayer + " Enter move row column (0, 1, 2):");

            int row, col;
            while (true) {
                    System.out.print("Row: ");
                    row = scanner.nextInt();
                    System.out.print("Column: ");
                    col = scanner.nextInt();

                    if (game.insertMove(row, col)) {
                        break;
                    } else {
                        System.out.println("Invalid move");
                    }
            }

            // Check for win or tie
            winner = game.checkWinner();
            if (winner == 'N') {
                game.switchPlayer();
            }
        }

        game.displayBoard();
        if (winner == 'T') {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Player " + winner + " wins!!!!");
        }
    }
}
