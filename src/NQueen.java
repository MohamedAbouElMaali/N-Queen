
//The NQueen Class is the main class that deals with solving the NQueen Problem from a given board size
public class NQueen {

    //The ChessBoard instance is used to access all required methods.
    //A filled nxn board is the used constructor
    ChessBoard chessBoard = new ChessBoard(fill(new Tile[5][5]));

    //The fill method takes in a 2d Tile Array and returns a full 2d Tile Array
    public Tile[][] fill(Tile[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                //Adding a Tile object to the corresponding part of the board
                board[row][col] = new Tile();
            }
        }
        return board;
    }

    //This is the main method that solves the N-Queen Problem.
    public boolean solve() {
        //Iterating through the board
        for (int row = 0; row < chessBoard.getBoard().length; row++) {
        for (int col = 0; col < chessBoard.getBoard()[row].length; col++) {
            //Calls the isOccupied method of the Tile located at [row][col] to check if it is safe to place a Queen without causing any conflicting errors with other Queens.
            if (chessBoard.getBoard()[row][col].isOccupied()) {
                 //Calls the place method from the chessBoard object
                chessBoard.place(row, col);

                //Starts a recursion process to check all combinations of Queen Placement and if the correct combination is found returns true and will go back to the first Stack
                if (solve()) {
                    return true;
                }
                else {
                    //Calls the unPlace method from the chessBoard object
                    chessBoard.unPlace(row, col);
                }
            }
        }}

        //After placing all Queens possible with the used combination this if statement checks to see if the combination used resulted in n amount of Queens
        if (chessBoard.getQueensPlaced() != chessBoard.getnAmount()) {
            return false;
        }

        //Returns True if combination worked
        return true;

    }
}
