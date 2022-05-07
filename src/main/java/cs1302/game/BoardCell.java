package cs1302.game;

/**
 * This class represents a {@code BoardCell} object.
 * Every Board Cell has an {@code xPos}, {@code yPos} and
 * a {@code type}.
 */
public class BoardCell {

    private int x;
    private int y;


    public BoardCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    } // getX

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(BoardCell other) {
        return x == other.x && y == other.y;
    }

} // Board Cell