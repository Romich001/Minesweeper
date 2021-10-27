package minesweeper;

import java.util.Objects;
//class presents the coordinate of the cell in the field
public class Coordinate implements Comparable<Coordinate> {
    final private int X;
    final private int Y;

    public Coordinate(int y, int x) {
        this.X = x;
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public int compareTo(Coordinate coordinate) {
        if (getX() == coordinate.getX() && getY() == coordinate.getY())
            return 0;
        if (getX() > coordinate.getX() && getY() > coordinate.getY()) return 1;
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return X == that.X && Y == that.Y;
    } //need this to List<> contains() and contansAll() in Game class

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }
}
