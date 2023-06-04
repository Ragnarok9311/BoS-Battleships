package battleships.ships;

import battleships.config.Config;
import battleships.util.Maths;
import battleships.util.Position;

public abstract class Ship {

    protected final int length;
    protected final boolean isHorizontal;
    protected Position[] positions;

    public Ship(int length) {
        this.length = length;
        this.isHorizontal = Maths.getRandomBoolean();
        this.positions = new Position[length];
        this.positions[0] = this.getRandomStartPosition();
        this.setPositions();
    }
    public void setPositions() {
        int x = positions[0].getX();
        int y = positions[0].getY();
        if (this.isHorizontal){
            for (int i = 1; i < this.positions.length; i++){
                positions[i] = new Position(++x, y);
            }
        }
        else {
            for (int i = 1; i < this.positions.length; i++){
                positions[i] = new Position(x, ++y);
            }
        }
    }

    private Position getRandomStartPosition() {
        int x = 0;
        int y = 0;

        if (this.isHorizontal) {
            x = Maths.getRandomNumberBetween(0, this.length);
            y = Maths.getRandomNumberBetween(0, Config.BOARD_HEIGHT - 1);
        } else {
            x = Maths.getRandomNumberBetween(0, Config.BOARD_WIDTH - 1);
            y = Maths.getRandomNumberBetween(0, this.length);
        }
        return new Position(x, y);
    }

    public int getLength() {
        return length;
    }

    public Position[] getPositions() {
        return positions;
    }
}