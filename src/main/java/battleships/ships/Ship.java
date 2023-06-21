package battleships.ships;

import battleships.config.Config;
import battleships.core.Game;
import battleships.util.Maths;
import battleships.util.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship {

    protected final int length;
    protected boolean isHorizontal;
    protected Position[] positions;
    protected boolean isPlayer1;
    protected int color;

    public Ship(int length, boolean isPlayer1) {
        this.isPlayer1 = isPlayer1;
        this.length = length;
        this.positions = new Position[this.length];
        this.setPositions();
        this.color = this.isPlayer1 ? Config.SHIP_COLOR_P1 : Config.SHIP_COLOR_P2;
    }

    private void setHorizontal() {
        this.isHorizontal = Maths.getRandomBoolean();
    }

    private void setPositions() {
        this.setHorizontal();
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
        this.checkAvailablePositions();
    }

    private void checkAvailablePositions() {
        List<Position> boardPositions = Game.BOARD_POSITIONS;
        List<Position> tempList = new ArrayList<>();

        boolean areAllPositionsValid = true;
        for (Position shipPosition : this.positions) {
            boolean isPositionValid = false;
            for (Position boardPosition : boardPositions) {
                if (shipPosition.equals(boardPosition)) {
                    tempList.add(boardPosition);
                    boardPositions.remove(boardPosition);
                    isPositionValid = true;
                    break;
                }
            }
            if (!isPositionValid) {
                areAllPositionsValid = false;
                break;
            }
        }

        if (areAllPositionsValid) return;
        boardPositions.addAll(tempList);
        this.setPositions();
    }

    private Position getRandomStartPosition() {
        int x;
        int y;

        if (this.isPlayer1) {
            if (this.isHorizontal) {
                x = Maths.getRandomNumberBetween(0, this.length);
                y = Maths.getRandomNumberBetween(0, Config.BOARD_HEIGHT);
            } else {
                x = Maths.getRandomNumberBetween(0, Config.BOARD_WIDTH / 2);
                y = Maths.getRandomNumberBetween(0, this.length);
            }
        } else {
            if (this.isHorizontal) {
                x = Maths.getRandomNumberBetween(Config.BOARD_WIDTH / 2, Config.BOARD_WIDTH - this.length);
                y = Maths.getRandomNumberBetween(0, Config.BOARD_HEIGHT);
            } else {
                x = Maths.getRandomNumberBetween(Config.BOARD_WIDTH / 2, Config.BOARD_WIDTH);
                y = Maths.getRandomNumberBetween(0, Config.BOARD_HEIGHT - this.length);
            }
        }
        return new Position(x, y);
    }

    public Position[] getPositions() {
        return positions;
    }

    public int getColor() {
        return color;
    }
}