package battleships.ships;

import battleships.config.Config;
import battleships.util.Maths;
import battleships.util.Position;

public abstract class Ship {

    protected final Position startPos;
    protected final int length;
    protected final boolean isHorizontal;

    public Ship(int length) {
        this.length = length;
        this.isHorizontal = Maths.getRandomBoolean();
        this.startPos = this.getRandomStartPosition();
    }

    private Position getRandomStartPosition() {
        if (this.isHorizontal) {
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

    public boolean isHorizontal() {
        return isHorizontal;
    }
}