import java.util.Arrays;
import java.util.Scanner;

public class TTTBug {
    static String player1 = "X";
    static String player2 = "O";

    public static void main(String[] args) {
        final String[] board = new String[9];
        intializeGame(board);
        play(board);

    }

    private static void intializeGame(String[] board) {
        System.out.println("Welcome to 2 Player Tic Tac Toe.");
        System.out.println("--------------------------------");

        for (int i = 0; i < board.length; ++i) {
            board[i] = String.valueOf(i + 1);
        }

        printBoard(board);

    }

    private static boolean play(String[] board) {
        Scanner scan = new Scanner(System.in);
        String currentPlayer = "X";
        boolean turn = true;
        while (turn) {
            System.out.println("Player " + currentPlayer + ": please enter the number of an available slot");
            int input = scan.nextInt();
            boolean range = (input <= 0 && input <= 9);
            boolean takenSlot = (board[input - 1] == player1) && (board[input - 1] == player2);

            if (range & takenSlot) {
                System.out.println("Invalid input: re-enter a valid slot number");
            } else {
                board[input - 1] = currentPlayer;
                if (currentPlayer == player1)
                    currentPlayer = player2;
                else
                    currentPlayer = player1;
            }
            printBoard(board);
            if (determineWinner(board)) {
                System.out.println("And we're done!");
            } else if (!slotsAvailable(board)) {
                System.out.println("It's a draw");
                System.out.println("And we're done!");
                turn = false;
            }
        }
        scan.close();
        return true;
    }

    private static void printBoard(String[] board) {
        System.out.println("/---|---|---\\");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("/---|---|---\\");

    }

    private static boolean slotsAvailable(String[] board) {
        boolean hasSlots = false;
        for (int i = 0; i < board.length; ++i) {
            if (Arrays.asList(board).contains(String.valueOf(i + 1))) {
                hasSlots = true;
                break;
            }
        }
        return hasSlots;
    }
    private static boolean determineWinner(String[] board) {
        String[] line = new String[8];
        String winner1 = "XXX";
        String winner2 = "OOO";
        line[0] = board[0] + board[1] + board[2];
        line[1] = board[3] + board[4] + board[5];
        line[2] = board[6] + board[7] + board[8];
        line[3] = board[0] + board[3] + board[5];
        line[4] = board[1] + board[3] + board[6];
        line[5] = board[2] + board[5] + board[7];
        line[6] = board[0] + board[4] + board[8];
        line[7] = board[2] + board[4] + board[6];

        for (String s : line) {
            if (s.equals(winner1)) {
                System.out.println("The winner is Player " + player1);
                return true;
            } else if (s.equals(winner2)) {
                System.out.println("The winner is Player " + player2);
                return true;
            }

        }
        return false;
    }
}