package cs1302.game;

import javafx.geometry.Orientation;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends VBox {

    enum CellType {
        BLANK,
        FOOD,
        SNAKE,
    }

    private Rectangle[][] board; // board where game will be played
    private TilePane playableGrid; // grid of Positions
    private BoardCell head; // head of the snake


    public Board(int width, int height) {
        // set up playable board
        this.playableGrid = new TilePane(Orientation.VERTICAL);
        playableGrid.setPrefRows(height);

        // initialize board and set all values to empty
        board = new Rectangle[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = new Rectangle(20, 20);
                playableGrid.getChildren().add(board[x][y]);
            }
        }

        this.getChildren().add(playableGrid);
    } // Constructor

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