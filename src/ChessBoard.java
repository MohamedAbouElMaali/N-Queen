import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ChessBoard {

    private Tile[][] board;
    private int nAmount;
    private int queensPlaced = 0;

    public ChessBoard(Tile[][] chessBoard) {
        board = chessBoard.clone();
        nAmount = chessBoard.length;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public int getnAmount() {
        return nAmount;
    }

    public int getQueensPlaced() {
        return queensPlaced;
    }

    public void erase() {
        int length = board.length;
        board = new Tile[length][length];
    }

    public void place(int row, int col) {
        board[row][col].occupy();
        queensPlaced++;
        updateSafety(false, row, col);
    }

    public void unPlace(int row, int col) {
        board[row][col].unOccupy();
        queensPlaced--;
        updateSafety(true, row, col);
    }

    private void updateSafety(boolean isSafe, int row, int col) {
        setSafety(row, col, isSafe);
        for (int nrow = 0; nrow < board.length; nrow++) {
            for (int ncol = 0; ncol < board[nrow].length; ncol++) {
                if (board[nrow][ncol].isOccupied) {
                    setSafety(nrow, ncol, false);
                }
            }
        }
        try {
            updateWebPage();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setSafety(int row, int col, boolean isSafe) {
        horizontalSaftey(row, isSafe);
        verticalSaftey(col, isSafe);
        diaganolSaftey(row, col, isSafe);
    }

    private void diaganolSaftey(int row, int col, boolean isSafe) {
        int m1 = 0;

        for (int nrow = row; nrow < board.length; nrow++) {
            int n = 0;
            for (int ncol = col + m1; ncol < board[nrow].length && n == 0; ncol++) {
                board[nrow][ncol].setTile(isSafe);
                n++;
            }
            m1++;
        }

        m1 = 0;

        for (int nrow = row; nrow < board.length; nrow++) {
            int n = 0;
            for (int ncol = col - m1; ncol >= 0 && n == 0; ncol--) {
                board[nrow][ncol].setTile(isSafe);
                n++;
            }
            m1++;
        }

        m1 = 0;

        for (int nrow = row; nrow >= 0; nrow--) {
            int n = 0;
            for (int ncol = col - m1; ncol >= 0 && n == 0; ncol--) {
                board[nrow][ncol].setTile(isSafe);
                n++;
            }
            m1++;
        }

        m1 = 0;

        for (int nrow = row; nrow >= 0; nrow--) {
            int n = 0;
            for (int ncol = col + m1; ncol < board[nrow].length && n == 0; ncol++) {
                board[nrow][ncol].setTile(isSafe);
                n++;
            }
            m1++;
        }
    }

    private void verticalSaftey(int col, boolean isSafe) {
        for (int row = 0; row < board.length; row++) {
            board[row][col].setTile(isSafe);
        }
    }

    private void horizontalSaftey(int row, boolean isSafe) {
        for (int col = 0; col < board[row].length; col++) {
            board[row][col].setTile(isSafe);
        }
    }

    public void updateWebPage() throws IOException {
        String htmlCode1 = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>N-Queen</title>\n" +
                "</head>\n" +
                "<body>";
        String htmlCode2 = "</body>\n" +
                "</html>";
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
            htmlCode1 += "<br>";
        }
        String htmlCodeComplete = htmlCode1 + htmlCode2;
            File file = new File("src/WebPage.html");
            FileWriter writer = new FileWriter(file);
            writer.write(htmlCodeComplete);
            writer.close();

    }
}
