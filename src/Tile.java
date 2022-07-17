public class Tile {
    public boolean isSafe = true;
    public boolean isOccupied = false;

    public void setTile(boolean isSafe) {
        this.isSafe = isSafe;
    }

    public boolean isOccupied() {
        return isSafe;
    }

    public void occupy() {
        isOccupied = true;
    }

    public void unOccupy() {
        isOccupied = false;
    }

}
