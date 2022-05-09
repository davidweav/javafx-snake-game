package cs1302.game;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;

import java.util.Random;

/**
 * this class combines all the components of the
 * snake game and contains the game loop.
 */
public class SnakeGame extends Game {

    private final int width = 58; // length of Positions
    private final int height = 34; // height of Positions
    private BoardCell food;
    private Snake snake;
    private Board board;
    private int score;
    private final EndGame endgame;
    private Snake.Direction nextDir;
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
        this.score = 1;
        this.endgame = new EndGame();
        this.endgame.onStartAgain(e -> this.playAgain());
        board.setBackground(Background.EMPTY);
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().add(board);
        this.positionInArea(board, 60, 30, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        play();
    }

    /**
     * Initializes the position of the snake and the movement it will have.
     * Also, generates the first apple.
     */
    @Override
    protected void init() {
        snake = new Snake(new BoardCell(width / 2, height / 2));
        nextDir = Snake.Direction.NO_DIRECTION;
        placeFood();
        step = 0;
        difficulty = 6;
    }

    /**
     * The main game loop. Every iteration of this method draws a new version
     * of the entire board.
     */
    @Override
    protected void update() {
        step++;
        if (step < difficulty) {
            return;
        }
        step = 0;

        snake.setDirection(nextDir);
        if (isLoss()) {
            displayEndGame();
        } // if

        if (snake.move(food)) {
            score += 3;
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

    /**
     * Checks to see if the game is lost.
     * @return true if the game over conditions are met and false otherwise.
     */
    public boolean isLoss() {
        for (BoardCell cell : snake.getBody()) {
            if (cell.equals(snake.getHead()) && snake.getHead() != cell) {
                return true;
            }
        }
        return snake.getHead().getX() == -1 || this.snake.getHead().getX() == width ||
                this.snake.getHead().getY() == -1 || this.snake.getHead().getY() == height;
    }

    /**
     * Generates the next apple in a random position.
     */
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

    /**
     * Displays the game over screen once the game is lost.
     */
    public void displayEndGame() {
        stop();
        this.getChildren().remove(board);
        endgame.update(score);
        this.getChildren().add(endgame);
    }

    /**
     * Starts the game over if the user chooses to do so in the game over screen.
     */
    public void playAgain() {
        this.getChildren().remove(endgame);
        this.getChildren().add(board);
        init();
        play();
    }

    /**
     * Handles the arrow keys controls.
     * @param event associated key event
     */
    @Override
    protected void handleKeyPressed(KeyEvent event) {
        super.handleKeyPressed(event);
        switch (event.getCode()) {
        case LEFT -> nextDir = Snake.Direction.LEFT;
        case RIGHT -> nextDir = Snake.Direction.RIGHT;
        case UP -> nextDir = Snake.Direction.UP;
        case DOWN -> nextDir = Snake.Direction.DOWN;
        }
    }

} // SnakeGame