package shejimos;

import java.util.Objects;

public class Point {
    int x;
    int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Point) {
            Point o1 = (Point) o;
            return this.x == o1.x && this.y == o1.y;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
