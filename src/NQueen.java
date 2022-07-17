import java.io.IOException;

public class NQueen {
    Tile[][] board = new Tile[10][10];

    ChessBoard chessBoard = new ChessBoard(fill());

    public Tile[][] fill() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new Tile();
            }
        }
        return board;
    }

    public boolean solve() {
        try {
            chessBoard.updateWebPage();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
            for (int row = 0; row < chessBoard.getBoard().length; row++) {
                for (int col = 0; col < chessBoard.getBoard()[row].length; col++) {
                    if (chessBoard.getBoard()[row][col].isSafe) {
                        chessBoard.place(row, col);
                        if (solve()) {
                            return true;
                        }
                        else {
                            chessBoard.unPlace(row, col);
                        }
                    }
                }
            }

            if (chessBoard.getQueensPlaced() != chessBoard.getnAmount()) {
                return false;
            }
                return true;

    }
}
