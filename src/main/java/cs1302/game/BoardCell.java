package cs1302.game;

/**
 * This class represents a {@code BoardCell} object.
 * Every Board Cell has an {@code xPos}, {@code yPos}.
 */
public class BoardCell {

    private int x;
    private int y;

    /**
     * Constructs a new board cell object.
     * @param x the x position the object will hold.
     * @param y the y position the object will hold.
     */
    public BoardCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x position of the board cell.
     * @return {@code x}.
     */
    public int getX() {
        return x;
    } // getX

    /**
     * Sets the x position of the board cell.
     * @param x the x position to be set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y position of the board cell.
     * @return {@code y}.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y position of the board cell.
     * @param y the y position to be set.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Checks to see if one board cell is equal to another board cell.
     * @param other the board cell to be compared to.
     * @return true if the two are equal and false otherwise.
     */
    public boolean equals(BoardCell other) {
        return x == other.x && y == other.y;
    }

} // Board Cell