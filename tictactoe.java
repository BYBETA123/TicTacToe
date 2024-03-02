// Overarching file for the tictactoe game

// import java.util.Scanner; // Importing the Scanner class to take in user input

public class tictactoe {

    public static void printBoard(Cell[]board){
        System.out.println("1|" + board[0].getValue() + board[3].getValue() + board[6].getValue());
        System.out.println("2|" + board[1].getValue() + board[4].getValue() + board[7].getValue());
        System.out.println("3|" + board[2].getValue() + board[5].getValue() + board[8].getValue());
        System.out.println("  ABC");
    }

    public static boolean ValidateMove(int[] playerInputArray, String playerInput, Cell[] board){
        if (playerInput.length() != 2){
            return false;
        }
        try { // Try to convert the input to integers return false if it fails
            playerInputArray[0] = playerInput.charAt(0) - 'A' + 1;
            playerInputArray[1] = playerInput.charAt(1) - '0';
        } catch (Exception e){
            return false;
        }

        int localx = playerInputArray[0];
        int localy = playerInputArray[1];

        if (localx<1 || localx>3 || localy<1 || localy>3){
            return false; // Out of bounds of the board
        }

        if (board[(localx-1)*3 + (localy-1)].getValue() != "N"){
            return false; // The piece is already occupied
        }
        
        return true;
    }

    public static int[] PlayerInput(int[] playerInputArray, Cell[] board){
        System.out.print("Enter the row and column of your move: ");
        // Taking Input
        String playerInput = Util.InputString();

        while (!ValidateMove(playerInputArray, playerInput, board)){
            System.out.print("Invalid move, try again: ");
            playerInput = Util.InputString();
            playerInputArray[0] = playerInput.charAt(0) - 'A' + 1;
            playerInputArray[1] = playerInput.charAt(1) - '0';
        }
        return playerInputArray;
    }

    public static int WinCondition(Cell[] board, String player){
        if(board[0].getValue() == player && board[1].getValue() == player && board[2].getValue() == player){
            return 2;
        }
        if(board[3].getValue() == player && board[4].getValue() == player && board[5].getValue() == player){
            return 3;
        }
        if(board[6].getValue() == player && board[7].getValue() == player && board[8].getValue() == player){
            return 4;
        }
        if(board[0].getValue() == player && board[3].getValue() == player && board[6].getValue() == player){
            return 5;
        }
        if(board[1].getValue() == player && board[4].getValue() == player && board[7].getValue() == player){
            return 6;
        }
        if(board[2].getValue() == player && board[5].getValue() == player && board[8].getValue() == player){
            return 7;
        }
        if(board[0].getValue() == player && board[4].getValue() == player && board[8].getValue() == player){
            return 8;
        }
        if(board[2].getValue() == player && board[4].getValue() == player && board[6].getValue() == player){
            return 9;
        }
        for (int i=0;i<9;i++){
            if (board[i].getValue() == "N"){
                return 0; // If there is an empty space, the game is not over
            }
        }
        // If no win condition is met and the board is full
        return 1;
    }

    public static void main(String[] args) {
        // Creating the board
        Cell[] board = new Cell[9];
        for (int i=0;i<9;i++){
            board[i] = new Cell(0, 0);
        }

        // Main Game
        boolean done = false;
        while (!done) {
            printBoard(board);
            //Player 1
            int playerInputArray[] = new int[2];
            PlayerInput(playerInputArray, board);
            int player1x = playerInputArray[0];
            int player1y = playerInputArray[1];
            board[(player1x-1)*3 + (player1y-1)].value = "X";
            done = WinCondition(board, "X")>0 ? true : false;
            if (!done){
                printBoard(board);
                //Player 2
                PlayerInput(playerInputArray, board);
                int player2x = playerInputArray[0];
                int player2y = playerInputArray[1];
                board[(player2x-1)*3 + (player2y-1)].value = "O";
                done = WinCondition(board, "O")>0 ? true : false;
            }
        }
        printBoard(board);
        System.out.println("Game Over");
        System.out.println("The winner is: " + (WinCondition(board, "X")>0 ? "Player 1" : "Player 2"));

    }
}