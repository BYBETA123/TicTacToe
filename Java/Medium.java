package Java;
import java.util.Random;

public class Medium extends Player {

    int[] newBoard = {0,0,0,0,0,0,0,0,0};
    public Medium(String token){
        setToken(token);
    }

    private int[] getEmptyCells(Cell[] board){
        int[] emptyCells = new int[9];
        int counter = 0;
        for (int i=0;i<9;i++){
            if (board[i].getValue() == "N"){
                emptyCells[counter] = i;
                counter++;
            }
        }
        return emptyCells;
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

    @Override
    public Cell[] move(Cell[] board) {

        // once the code above is completed, we have a list of empty cells, pick one at random
        for (int i=0;i<9;i++){
            if (board[i].getValue() == "N"){
                board[i].value = getToken(); //set the value
                if (WinCondition(board, getToken()) != 0){
                    return board;
                }
                board[i].value = "N"; // Set the value back
            }
        }
        for (int i=0;i<9;i++){
            if (board[i].getValue() == "N"){
                board[i].value = (getToken()=="X") ? "O" : "X"; //opponent color
                if (WinCondition(board, ((getToken()=="X") ? "O" : "X")) != 0){
                    board[i].value = getToken();
                    return board;
                }
                board[i].value = "N";
            }
        }
        // if we are not in immediate danger, pick a random spot
        newBoard = getEmptyCells(board);
        Random rand = new Random();
        int randomIndex = rand.nextInt(newBoard.length);
        board[newBoard[randomIndex]].value = getToken(); //set the value
        return board;
    }
    
}
