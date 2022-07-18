import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//This is the class that deals with the Chess Board and placing Queens
public class ChessBoard {

    //The board
    private Tile[][] board;

    //The board size
    private final int nAmount;

    //The amount of Queens Placed
    private int queensPlaced = 0;

    //A constructor that sets the board variable to the given Tile[][] parameter
    public ChessBoard(Tile[][] chessBoard) {
        board = chessBoard.clone();
        nAmount = chessBoard.length;
    }

    //Returns the board
    public Tile[][] getBoard() {
        return board;
    }

    //Returns the nAmount variable
    public int getnAmount() {
        return nAmount;
    }

    //Returns the queensPlaced variable
    public int getQueensPlaced() {
        return queensPlaced;
    }

    //Places a queen and updates the board safety
    public void place(int row, int col) {
        //Calls the occupy method of the Tile class which registers the corresponding Tile as being occupied by a Queen
        board[row][col].occupy();
        //Updates the queensPlaced variable
        queensPlaced++;
        //Calls the updateSafety method to update the status of the isSafe variables of the Tiles in the board
        updateSafety(false, row, col);
        //Calls the updateWebPage method to update the html file to reflect changes in status of the Tiles on the board
        updateWebPage();
    }

    //Unplaces a queen and updates the board safety
    public void unPlace(int row, int col) {
        //Calls the unOccupy method of the Tile class which registers the corresponding Tile as being unOccupied by a Queen
        board[row][col].unOccupy();
        //Updates the queensPlaced variable
        queensPlaced--;
        //Calls the updateSafety method to update the status of the isSafe variables of the Tiles in the board
        updateSafety(true, row, col);
        //Calls the updateWebPage method to update the html file to reflect changes in status of the Tiles on the board
        updateWebPage();
    }
    
    //Updates the isSafe status of the corresponding Queen at (row, col)
    private void updateSafety(boolean isSafe, int row, int col) {
        //Calls the setSafety method to change the isSafe status to the isSafe Parameter
        setSafety(row, col, isSafe);
        /*Calls the setSafety method for every Queen on the board 
        to combat any false statues of the isSafe variable 
        of the Tiles due to resetting the status of the last Queen
        */
        for (int nrow = 0; nrow < board.length; nrow++) {
        for (int ncol = 0; ncol < board[nrow].length; ncol++) {
            if (board[nrow][ncol].isOccupied) {
                setSafety(nrow, ncol, false);
            }
        }}
    }

    //Updates the safety status of the Tiles in the board to the isSafe parameter
    private void setSafety(int row, int col, boolean isSafe) {
        horizontalSaftey(row, isSafe);
        verticalSaftey(col, isSafe);
        diaganolSaftey(row, col, isSafe);
    }

    //Updates the diagonal safety status of the Tiles in the board to the isSafe parameter
    private void diaganolSaftey(int row, int col, boolean isSafe) {
        //There are 4 four loops each one represents one diagonal direction: Top-Left, Top-Right, Bottom-Left, Bottom-Right

        //This variable is used to determine what the offset to the col variable in each for loop should be
        int colToAdd = 0;

        //Bottom-Right
        for (int nrow = row; nrow < board.length; nrow++) {
            //Stops the code from setting the safety horizontally by only changing one column per row
            int n = 0;
            for (int ncol = col + colToAdd; ncol < board[nrow].length && n == 0; ncol++) {
                //Sets the isSafe variable of the corresponding Tile to the isSafe parameter
                board[nrow][ncol].setTile(isSafe);
                //After setting one column's of the row safety it increments n by 1
                n++;
            }
            colToAdd++;
        }

        //Resetting the colToAdd
        colToAdd = 0;

        //Bottom-Left
        for (int nrow = row; nrow < board.length; nrow++) {
            int n = 0;
            for (int ncol = col - colToAdd; ncol >= 0 && n == 0; ncol--) {
                //Sets the isSafe variable of the corresponding Tile to the isSafe parameter
                board[nrow][ncol].setTile(isSafe);
                n++;
            }
            colToAdd++;
        }

        //Resetting the colToAdd
        colToAdd = 0;

        //Top-Left
        for (int nrow = row; nrow >= 0; nrow--) {
            int n = 0;
            for (int ncol = col - colToAdd; ncol >= 0 && n == 0; ncol--) {
                //Sets the isSafe variable of the corresponding Tile to the isSafe parameter
                board[nrow][ncol].setTile(isSafe);
                n++;
            }
            colToAdd++;
        }

        //Resetting the colToAdd
        colToAdd = 0;

        //Top-Right
        for (int nrow = row; nrow >= 0; nrow--) {
            int n = 0;
            for (int ncol = col + colToAdd; ncol < board[nrow].length && n == 0; ncol++) {
                //Sets the isSafe variable of the corresponding Tile to the isSafe parameter
                board[nrow][ncol].setTile(isSafe);
                n++;
            }
            colToAdd++;
        }
    }

    //Updates the vertical safety status of the Tiles in the board to the isSafe parameter
    private void verticalSaftey(int col, boolean isSafe) {
        //Iterates through each row.
        for (int row = 0; row < board.length; row++) {
            //Sets the isSafe variable of the corresponding Tile to the isSafe parameter
            board[row][col].setTile(isSafe);
        }
    }

    //Updates the horizontal safety status of the Tiles in the board to the isSafe parameter
    private void horizontalSaftey(int row, boolean isSafe) {
        //Iterates through every column in a specifed row
        for (int col = 0; col < board[row].length; col++) {
            //Sets the isSafe variable of the corresponding Tile to the isSafe parameter
            board[row][col].setTile(isSafe);
        }
    }
    
    //Updates the .html file to reflect any changes in the board
    public void updateWebPage() {
        String htmlCode1 = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>N-Queen</title>\n" +
                "</head>\n" +
                "<body>";
        String htmlCode2 = "</body>\n" +
                "</html>";
        //The HTML code for inserting image 1-3
        String image1 = "<img src=\"Images/image1.png\"  width=\"10\" height=\"10\" border=\"1\" style=\"TOP:2px;LEFT:270px;\">";
        String image2 = "<img src=\"Images/image2.png\"  width=\"10\" height=\"10\" border=\"1\" style=\"TOP:2px;LEFT:270px;\">";
        String image3 = "<img src=\"Images/image3.png\"  width=\"10\" height=\"10\" border=\"1\" style=\"TOP:2px;LEFT:270px;\">";

        for(Tile[] Tiles: board) {
            for (Tile Tile: Tiles) {
                if (Tile.isSafe) {
                    htmlCode1 += image1;
                }
                else {
                    if (Tile.isOccupied) {
                        htmlCode1 += image2;
                    }
                    else {
                        htmlCode1 += image3;
                    }
                }
            }
            //Inserts a line break to go to the next row
            htmlCode1 += "<br>";
        }
        //The full HTML Code
        String htmlCodeComplete = htmlCode1 + htmlCode2;
        try {
            //Writes the File
            File file = new File("src/WebPage.html");
            FileWriter writer = new FileWriter(file);
            writer.write(htmlCodeComplete);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
