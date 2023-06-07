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
        this.setPositions();
    }

    private void setPositions() {
        this.positions[0] = this.getRandomStartPosition();
        int x = positions[0].getX();
        int y = positions[0].getY();

        for (int i = 1; i < this.positions.length; i++){
            if (this.isHorizontal){
                x++;
            } else {
                y++;
            }
            this.positions[i] = new Position(x, y);
        }
    }

    private Position getRandomStartPosition() {
        int x;
        int y;

        if (this.isHorizontal) {
            x = Maths.getRandomNumberBetween(0, this.length);
            y = Maths.getRandomNumberBetween(0, Config.BOARD_HEIGHT - 1);
        } else {
            x = Maths.getRandomNumberBetween(0, Config.BOARD_WIDTH - 1);
            y = Maths.getRandomNumberBetween(0, this.length);
        }
        return new Position(x, y);
    }

    public Position[] getPositions() {
        return positions;
    }
}