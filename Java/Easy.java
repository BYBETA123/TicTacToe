package Java;
import java.util.Random;
public class Easy extends Player{

    private int[] newBoard = {0,0,0,0,0,0,0,0,0};
    public Easy(String token){
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

    @Override
    public Cell[] move(Cell[] board) {

        newBoard = getEmptyCells(board);
        // once the code above is completed, we have a list of empty cells, pick one at random
        Random rand = new Random();
        int randomIndex = rand.nextInt(newBoard.length);
        board[newBoard[randomIndex]].value = getToken(); //set the value
        return board;
    }
    
}
