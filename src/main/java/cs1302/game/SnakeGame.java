package cs1302.game;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;

import java.util.Random;

public class SnakeGame extends Game {

    private final int width = 58; // length of Positions
    private final int height = 34; // height of Positions
    private BoardCell food;
    private Snake snake;
    private Board board;
    private int difficulty;
    private int step;

    /**
     * Construct a {@code SnakeGame} object.
     *
     * @param width  minimum game region width
     * @param height minimum game region height
     * @param fps    target frames per second (FPS)
     */
    public SnakeGame(int width, int height, int fps) {
        super(width, height, fps);
        this.board = new Board(this.width, this.height);
        board.setBackground(Background.EMPTY);
        this.getChildren().add(board);
        this.positionInArea(board, 60, 30, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        play();
    }

    @Override
    protected void init() {
        snake = new Snake(new BoardCell(width / 2, height / 2));
        placeFood();
        step = 0;
        difficulty = 6;
    }

    @Override
    protected void update() {
        step++;
        if (step < difficulty) {
            return;
        }
        step = 0;
        isKeyPressed(KeyCode.LEFT, () -> snake.setDirection(Snake.Direction.LEFT));
        isKeyPressed(KeyCode.RIGHT, () -> snake.setDirection(Snake.Direction.RIGHT));
        isKeyPressed(KeyCode.UP, () -> snake.setDirection(Snake.Direction.UP));
        isKeyPressed(KeyCode.DOWN, () -> snake.setDirection(Snake.Direction.DOWN));
        if (snake.move(food)) {
            placeFood();
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Board.CellType cellType = null;
                if (food.getX() == x && food.getY() == y) {
                    cellType = Board.CellType.FOOD;
                } else if (snake.isBody(x, y)) {
                    cellType = Board.CellType.SNAKE;
                } else {
                    cellType = Board.CellType.BLANK;
                }
                board.setCellType(x, y, cellType);
            }
        }
    }

    public void placeFood() {
        Random rng = new Random();
        while (true) {
            int x = rng.nextInt(width);
            int y = rng.nextInt(height);
            if (!snake.isBody(x, y)) {
                food = new BoardCell(x, y);
                break;
            } // if
        } // while
    } // placeFood

} // SnakeGame