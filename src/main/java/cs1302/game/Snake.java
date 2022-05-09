package cs1302.game;

import java.util.LinkedList;

/**
 * The snake class represents the snake in the arcade game snake.
 * A snake object consists of a linked list of board cells that make up
 * the body of the snake. The snake also always has a {@link Direction}, which
 * indicates which way the snake will be moving.
 */
public class Snake {

    /**
     * This enumeration represents the five states of motion a snake can
     * have.
     */
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NO_DIRECTION;
    } // SnakeDirection enum

    private LinkedList<BoardCell> body;
    private Direction direction;
    private int growth;

    /**
     * Constructs a new snake object.
     * @param body the starting position of the snake.
     */
    public Snake(BoardCell body) {
        this.body = new LinkedList<BoardCell>();
        this.body.add(body);
        this.direction = Direction.DOWN;
    }

    /**
     * Checks to see if the given cordinate contains a snake block.
     * @param x the x position to be checked.
     * @param y the y position to be checked.
     * @return true if the position given is a snake block and false otherwise.
     */
    public boolean isBody(int x, int y) {
        for (BoardCell pos : body) {
            if (pos.getX() == x && pos.getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the front position of the snake.
     * @return the front position of the snake.
     */
    public BoardCell getHead() {
        return this.body.get(0);
    }

    /**
     * Returns the direction that the snake is moving.
     * @return the direction that the snake is moving.
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Sets the direction that the snake will move.
     * @param direction the direction that the snake will move.
     */
    public void setDirection(Direction direction) {
        if (direction.equals(Direction.DOWN) && this.direction.equals(Direction.UP)) {
            return;
        } else if (direction.equals(Direction.UP) && this.direction.equals(Direction.DOWN)) {
            return;
        } else if (direction.equals(Direction.LEFT) && this.direction.equals(Direction.RIGHT)) {
            return;
        } else if (direction.equals(Direction.RIGHT) && this.direction.equals(Direction.LEFT)) {
            return;
        }
        this.direction = direction;
    }

    /**
     * Moves the snake one unit in the current direction it is taking.
     * This method also checks to see if the snake has eaten an apple.
     * @param food the next position to be moved to
     * @return true if the next position is an apple and false otherwise.
     */
    public boolean move(BoardCell food) {
        boolean ateFood = false;
        BoardCell newHead = null;
        BoardCell currHead = getHead();
        switch (direction) {
        case NO_DIRECTION:
            return false;
        case UP:
            newHead = new BoardCell(currHead.getX(), currHead.getY() - 1);
            break;
        case DOWN:
            newHead = new BoardCell(currHead.getX(), currHead.getY() + 1);
            break;
        case LEFT:
            newHead = new BoardCell(currHead.getX() - 1, currHead.getY());
            break;
        case RIGHT:
            newHead = new BoardCell(currHead.getX() + 1, currHead.getY());
            break;
        }
        body.addFirst(newHead);

        if (newHead.equals(food)) {
            growth += 3;
            ateFood = true;
        }
        if (growth > 0) {
            growth--;
        } else {
            body.removeLast();
        }
        return ateFood;
    }

    /**
     * Returns the body or the linked list that contains the snake body positions.
     * @return the body or the linked list that contains the snake body positions.
     */
    public LinkedList<BoardCell> getBody() {
        return this.body;
    }


} // Snake