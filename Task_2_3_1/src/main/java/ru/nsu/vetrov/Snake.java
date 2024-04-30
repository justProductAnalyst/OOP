package ru.nsu.vetrov;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    private LinkedList<Point> points;
    private Direction direction;
    private int width, height;

    public Snake(Point startPosition, int width, int height) {
        points = new LinkedList<>();
        points.add(startPosition);
        this.direction = Direction.RIGHT;
        this.width = width;
        this.height = height;
    }

    public Snake(Point startPosition) {
        points = new LinkedList<>();
        points.add(startPosition);
        this.direction = Direction.RIGHT;
    }

    public void move() {
        Point newHead = peekNextMove();
        points.addFirst(newHead);
        points.removeLast();
    }

    public Point peekNextMove() {
        Point head = points.getFirst();
        int newX = head.getX();
        int newY = head.getY();

        switch (direction) {
            case UP:
                newY = (newY - 1 + height) % height;
                break;
            case DOWN:
                newY = (newY + 1) % height;
                break;
            case LEFT:
                newX = (newX - 1 + width) % width;
                break;
            case RIGHT:
                newX = (newX + 1) % width;
                break;
        }

        return new Point(newX, newY);
    }


    public void grow() {
        Point newHead = peekNextMove();
        points.addFirst(newHead);
    }

    public boolean intersects(Point point) {
        return points.contains(point);
    }

    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    public List<Point> getPoints() {
        return points;
    }
}
