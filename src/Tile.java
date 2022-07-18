//Tile Class: Makes up th 2d ChessBoard Array and deals with classifying each square
public class Tile {
    //Instance Variables

    //True if placing a queen on the Tile will not cause any problems
    public boolean isSafe = true;
    //True if their is currently a queen on the Tile
    public boolean isOccupied = false;

    //Sets the status of the "isSafe" boolean to the given parameter
    public void setTile(boolean isSafe) {
        this.isSafe = isSafe;
    }

    //Returns the status of the "isSafe" boolean of the Tile
    public boolean isOccupied() {
        return isSafe;
    }

    //Sets the value of the "isOccupied" boolean to true
    public void occupy() {
        isOccupied = true;
    }


    //Sets the value of the "isOccupied" boolean to false
    public void unOccupy() {
        isOccupied = false;
    }

}
