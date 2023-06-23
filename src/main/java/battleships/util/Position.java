package battleships.util;

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position getRandomPosition(int minX, int maxX, int minY, int maxY) {
        return new Position(
            Maths.getRandomNumberBetween(minX, maxX),
            Maths.getRandomNumberBetween(minY, maxY)
        );
    }

    public boolean equals(Position other) {
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return String.format("%d | %d", this.x, this.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}