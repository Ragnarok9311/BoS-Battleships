package battleships.ships;

import battleships.config.Config;
import battleships.util.Direction;
import battleships.util.Maths;
import battleships.util.Position;

public class Ship {

    protected final Position startPos;
    protected final int length;
    protected final Direction direction;

    public Ship(int length, Direction direction) {
        this.length = length;
        this.direction = direction;
        this.startPos = this.getRandomStartPosition();
    }

    private Position getRandomStartPosition() {
        if (this.direction == Direction.HORIZONTAL) {
            return this.getRandomHorizontalStartPosition();
        } else {
            return this.getRandomVerticalStartPosition();
        }
    }

    private Position getRandomHorizontalStartPosition() {
        int x = Maths.getRandomNumberBetween(0, this.length);
        int y = Maths.getRandomNumberBetween(0, Config.BOARD_HEIGHT - 1);
        return new Position(x, y);
    }

    private Position getRandomVerticalStartPosition() {
        int x = Maths.getRandomNumberBetween(0, Config.BOARD_WIDTH - 1);
        int y = Maths.getRandomNumberBetween(0, this.length);
        return new Position(x, y);
    }

    public Position getStartPos() {
        return startPos;
    }

    public int getLength() {
        return length;
    }

    public Direction getDirection() {
        return direction;
    }
}