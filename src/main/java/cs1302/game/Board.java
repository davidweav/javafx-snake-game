package cs1302.game;

import javafx.geometry.Orientation;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class represents the board of the snake game.
 * Every Board contains a two-dimensional array of rectangles, which
 * is formated using a {@link TilePane}.
 */
public class Board extends VBox {

    /**
     * This enumeration represents the three types of rectangles a board can
     * consist of.
     */
    enum CellType {
        BLANK,
        FOOD,
        SNAKE,
    }

    private Rectangle[][] board; // board where game will be played
    private TilePane playableGrid; // grid of Positions
    private BoardCell head; // head of the snake


    /**
     * Constructs a new board object.
     * @param width the width the board will take.
     * @param height the height the board will take.
     */
    public Board(int width, int height) {
        // set up playable board
        this.playableGrid = new TilePane(Orientation.VERTICAL);
        playableGrid.setPrefRows(height);
        // initialize board and set all values to empty
        board = new Rectangle[width][height];
        // Loop through board and initialize each rectangle
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = new Rectangle(20, 20);
                playableGrid.getChildren().add(board[x][y]);
            }
        }
        this.getChildren().add(playableGrid);
    } // Constructor

    /**
     * Colors a rectangle based on a specified position of the rectangle
     * and the {@link CellType} of the rectangle.
     * @param x the x position of the rectangle to be colored.
     * @param y the y position of the rectangle to be colored.
     * @param cellType the type that the rectangle will take.
     */
    public void setCellType(int x, int y, CellType cellType) {
        Color color = Color.RED;
        switch (cellType) {
        case BLANK:
            color = Color.BLUE;
            break;
        case SNAKE:
            color = Color.GREEN;
            break;
        case FOOD:
            color = Color.RED;
            break;
        }
        board[x][y].setFill(color);
    }
} // Board