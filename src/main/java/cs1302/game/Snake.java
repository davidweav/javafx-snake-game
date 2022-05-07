package cs1302.game;

import java.util.LinkedList;

public class Snake {

    public boolean isBody(int x, int y) {
        for (BoardCell pos : body) {
            if (pos.getX() == x && pos.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    } // SnakeDirection enum

    private LinkedList<BoardCell> body;
    private Direction direction;
    private int growth;

    public Snake(BoardCell body) {
        this.body = new LinkedList<BoardCell>();
        this.body.add(body);
        this.direction = Direction.DOWN;
    }

    public BoardCell getHead() {
        return this.body.get(0);
    }

    public Direction getDirection() {
        return this.direction;
    }

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

    public boolean move(BoardCell food) {
        boolean ateFood = false;
        BoardCell newHead = null;
        BoardCell currHead = getHead();
        switch (direction) {
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



} // Snake