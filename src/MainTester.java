//This is where the program is run
public class MainTester {

    //Main method
    public static void main(String[] args) {
        //The N-Queen object
        NQueen test = new NQueen();

        //Running the solve method of the N-Queen Object
        test.solve();

        System.out.println(test.chessBoard.getQueensPlaced());
    }
}
