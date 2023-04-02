import java.util.Arrays;
import java.util.Scanner;

public class TTT {
    // Global variables that can be used across all methods throughout the TTT class
    static String player1 = "X";
    static String player2 = "O";

    // the main method is the central method in the class as this will get executed
    // at run time
    public static void main(String[] args) {
        // local variable that can only be used in the method that it is defined in
        // define an "constant" String array, which after initialization cannot be
        // changed
        // it has 9 elments, with index count starting at 0 and highest index at 8
        final String[] board = new String[9];

        // method intializeGame is called and the board array is passed as a parameter
        intializeGame(board);

        play(board);

    }

    // definition of a method that does not return a value (void) but takes in the
    // string array as a parameter
    private static void intializeGame(String[] board) {
        // Welcome message
        System.out.println("Welcome to 2 Player Tic Tac Toe.");
        System.out.println("--------------------------------");

        // to show numbers 1-9 on the board, we are setting each element of the array
        // board to a string 1-9
        // we are using the forloop to do so
        // we are terminating the for loop at the length of the board array
        for (int i = 0; i < board.length; ++i) {
            // assigning an value of type String to each element in the board array
            // String.valueOf(i+1) takes an integer (i+1) and makes a String out of it
            board[i] = String.valueOf(i + 1);
        }
        // now print the board for the use by calling printBoard(board)
        printBoard(board);
        return;
    }

    private static boolean play(String[] board) {
        // Scanner helps us to read a variety to data types from the command line
        Scanner scan = new Scanner(System.in);
        String currentPlayer = "X";
        // boolean value is either true or false.
        // turn a variable we are using to run until someone won the game or a draw is
        // reached
        boolean turn = true;
        // using a while loop that will terminate when turn = false
        while (turn) {
            System.out.println("Player " + currentPlayer + ": please enter the number of an available slot");
            // scanner using nextInt() to read an integer
            int input = scan.nextInt();

            // boolean expressions allow us to formulate logical statements
            // operators are &&=and ||=or "!=" = notequal etc
            boolean range = (input <= 0 && input <= 9);
            boolean takenSlot = (board[input - 1] == player1) || (board[input - 1] == player2);

            // if-else and nested if statements
            if (range || takenSlot) {
                System.out.println("Invalid input: re-enter a valid slot number");
            } else {
                board[input - 1] = currentPlayer;
                // nested if-else statement
                if (currentPlayer == player1)
                    currentPlayer = player2;
                else
                    currentPlayer = player1;
            }
            printBoard(board);
            // the method determineWinner returns a boolean (True or false), and we can
            // use it directly in an if-statement
            // if-else-if
            if (determineWinner(board)) {
                System.out.println("And we're done!");
                turn = false;
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
        // printing a 2d grid and filling each value of the board through its elements
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
        line[3] = board[0] + board[3] + board[6];
        line[4] = board[1] + board[4] + board[7];
        line[5] = board[2] + board[5] + board[8];
        line[6] = board[0] + board[4] + board[8];
        line[7] = board[2] + board[4] + board[6];

        // for-statement that iterates through every value of an array and places it
        // into the variable s

        for (String s : line) {
            // string compares only work with s.equals(x) not with == or != which we use for
            // integers or booleans
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




