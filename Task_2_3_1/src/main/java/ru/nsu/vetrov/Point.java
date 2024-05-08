package ru.nsu.vetrov;

/**
 * Represents a point in a two-dimensional space with x and y coordinates.
 */
public class Point {
    private int pointX;
    private int pointY;

    /**
     * Constructs a new point with the specified x and y coordinates.
     *
     * @param pointX the x-coordinate of this point
     * @param pointY the y-coordinate of this point
     */
    public Point(int pointX, int pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate
     */
    public int getPointX() {
        return pointX;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate
     */
    public int getPointY() {
        return pointY;
    }

    /**
     * Compares this point to the specified object. The result is true if and only if
     * the argument is not null and is a Point object that has the same x and y coordinates
     * as this object.
     *
     * @param obj the object to compare with
     * @return true if the given object represents a point equivalent to this point, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point p = (Point) obj;
            return pointX == p.pointX && pointY == p.pointY;
        }
        return false;
    }

    /**
     * Returns a hash code for this point. The hash code is calculated by combining
     * the hash codes of the x and y coordinates using the formula: x * 31 + y.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return pointX * 31 + pointY;
    }
}
