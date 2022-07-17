public class Queen {
    public void place(Tile[][] board, int col, int row) {
        board[row][col].occupy();
    }
}
