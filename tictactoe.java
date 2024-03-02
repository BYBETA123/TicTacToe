public class tictactoe {

    public static void printBoard(Cell[]board){
        System.out.println("1|" + board[0].getValue() + board[3].getValue() + board[6].getValue());
        System.out.println("2|" + board[1].getValue() + board[4].getValue() + board[7].getValue());
        System.out.println("3|" + board[2].getValue() + board[5].getValue() + board[8].getValue());
        System.out.println("  ABC");
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
        // If no win condition is met and the board is full and is a draw
        return 1;
    }

    public static void main(String[] args) {
        // Creating the board
        Cell[] board = new Cell[9];
        for (int i=0;i<9;i++){
            board[i] = new Cell(0, 0);
        }
        Player player1 = new Human("X");
        Player player2 = new Easy("O");
        // Main Game
        int done = 0;
        while (done==0) {
            printBoard(board);
            //Player 1

            board = player1.move(board);
            done = WinCondition(board, "X");
            if (done==0){
                printBoard(board);
                //Player 2
                player2.move(board);
                done = WinCondition(board, "O");
            }
        }
        printBoard(board);
        System.out.println("Game Over");
        if (done == 1){
            System.out.println("The game is a draw");
        }
        else{
            System.out.println("The winner is: " + (WinCondition(board, "X")>0 ? "Player 1" : "Player 2"));
        }
    }
}