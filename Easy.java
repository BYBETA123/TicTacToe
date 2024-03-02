import java.util.Random;
public class Easy extends Player{

    public Easy(String token){
        setToken(token);
    }

    @Override
    public Cell[] move(Cell[] board) {
        int[] newBoard = {0,0,0,0,0,0,0,0,0};
        int counter= 0;
        for (int i=0;i<9;i++){// gather a list of all the empty cells
            if (board[i].getValue() == "N"){
                newBoard[counter] = i;
                counter ++;
            }
        }
        // once the code above is completed, we have a list of empty cells, pick one at random
        Random rand = new Random();
        int randomIndex = rand.nextInt(newBoard.length);
        board[newBoard[randomIndex]].value = getToken(); //set the value
        return board;
    }
    
}
