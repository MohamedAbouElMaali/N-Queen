public class MainTester {
    public static void main(String[] args) {
        NQueen test = new NQueen();
        test.solve();
        System.out.println(test.chessBoard.getQueensPlaced());
    }
}
