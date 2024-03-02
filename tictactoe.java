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

    public static int TicTacMute(Player player1, Player player2){
        // Creating the board
        Cell[] board = new Cell[9];
        for (int i=0;i<9;i++){
            board[i] = new Cell(0, 0);
        }
        // Main Game
        int done = 0;
        while (done==0) {
            //Player 1
            board = player1.move(board);
            done = WinCondition(board, "X");
            if (done==0){
                //Player 2
                board = player2.move(board);
                done = WinCondition(board, "O");
            }
        }
        if (done == 1){
            return 3;
        }
        else{
            if (WinCondition(board, "X")>0){
                return 1;
            }
            else{
                return 2;
            }

        }
    }

    public static int MainTicTacToe(Player player1, Player player2){
        // Creating the board
        Cell[] board = new Cell[9];
        for (int i=0;i<9;i++){
            board[i] = new Cell(0, 0);
        }
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
            return 3;
        }
        else{
            if (WinCondition(board, "X")>0){
                System.out.println("The winner is Player 1");
                return 1;
            }
            else{
                System.out.println("The winner is Player 2");
                return 2;
            }

        }
    }

    public static void TicTacToeLoop(){
        int win =0;
        int player1win = 0;
        int player2win = 0;
        int draw = 0;
        float total = 10000;
        for (int i=0;i<total;i++){
            win = TicTacMute(new Hard("X"), new Hard("O"));
            if (win == 1){
                player1win++;
            }
            if (win == 2){
                player2win++;
            }
            if (win == 3){
                draw++;
            }
        }
        System.out.println("Player 1 wins: " + player1win + " with a win rate of " + (player1win/total)*100 + "%");
        System.out.println("Player 2 wins: " + player2win + " with a win rate of " + (player2win/total)*100 + "%");
        System.out.println("Draws: " + draw + " with a draw rate of " + (draw/total)*100 + "%");
    }

    public static void main(String[] args) {
        // int winner = MainTicTacToe(new Easy("X"), new Easy("O"));
        TicTacToeLoop();
    }
}