public class Human extends Player{

    private String playerInput;
    private int playerInputArray[] = new int[2];
    public Human(String token){
        setToken(token);
    }


    public boolean ValidateMove(int[] playerInputArray, String playerInput, Cell[] board){
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


    public int[] PlayerInput(int[] playerInputArray, Cell[] board){
        System.out.print("Enter the row and column of your move: ");
        
        playerInput = Util.InputString();

        while (!ValidateMove(playerInputArray, playerInput, board)){
            System.out.print("Invalid move, try again: ");
            playerInput = Util.InputString();
            playerInputArray[0] = playerInput.charAt(0) - 'A' + 1;
            playerInputArray[1] = playerInput.charAt(1) - '0';
        }
        return playerInputArray;
    }
    @Override
    public Cell[] move(Cell[] board){
        playerInputArray = new int[2];
        PlayerInput(playerInputArray, board);
        int player1x = playerInputArray[0];
        int player1y = playerInputArray[1];
        board[(player1x-1)*3 + (player1y-1)].value = getToken();
        return board;
    }

}
