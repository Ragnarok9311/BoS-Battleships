package battleships.ships;

import battleships.config.Config;
import battleships.core.Game;
import battleships.logging.Logger;
import battleships.util.Maths;
import battleships.util.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship {

    private static final String TAG = Ship.class.getSimpleName();
    private static final Logger LOGGER = Logger.getInstance();

    protected final int length;
    protected boolean isHorizontal;
    protected Position[] positions;

    public Ship(int length) {
        LOGGER.log("Initialize new ship", TAG);
        this.length = length;
        this.setHorizontal();
        this.positions = new Position[this.length];
        this.setPositions();
    }

    private void setHorizontal() {
        this.isHorizontal = Maths.getRandomBoolean();
        LOGGER.debug("Direction: " + this.isHorizontal, TAG);
    }

    private void setPositions() {
//        this.setHorizontal();
        this.positions[0] = this.getRandomStartPosition();
        int x = this.positions[0].getX();
        int y = this.positions[0].getY();

        for (int i = 0; i < this.length; i++) {
            if (this.isHorizontal) {
                x++;
            } else {
                y++;
            }
            this.positions[i] = new Position(x, y);
        }
//        this.checkAvailablePositions();
    }

    private void checkAvailablePositions() {
        List<Position> boardPositions = Game.BOARD_POSITIONS;
        List<Position> tempList = new ArrayList<>();

        boardPositions.forEach(position -> {
            for (Position shipPosition : this.positions) {
                if (this.isValidPosition(position, shipPosition)) {
                    tempList.add(position);
                    boardPositions.remove(position);
                } else {
                    boardPositions.addAll(tempList);
                    this.setPositions();
                }
            }
        });
    }

    private boolean isValidPosition(Position boardPosition, Position shipPosition) {
        return shipPosition.getX() == boardPosition.getX() && shipPosition.getY() == boardPosition.getY();
    }

    private Position getRandomStartPosition() {
        int x;
        int y;

        if (this.isHorizontal) {
            x = Maths.getRandomNumberBetween(0, this.length);
            y = Maths.getRandomNumberBetween(0, Config.BOARD_HEIGHT);
        } else {
            x = Maths.getRandomNumberBetween(0, Config.BOARD_WIDTH);
            y = Maths.getRandomNumberBetween(0, this.length);
        }
        return new Position(x, y);
    }

    public Position[] getPositions() {
        return positions;
    }
}