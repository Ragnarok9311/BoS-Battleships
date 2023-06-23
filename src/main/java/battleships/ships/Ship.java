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
        return this.isPlayer1 ? this.getStartPositionForPlayer1() : this.getStartPositionForPlayer2();
    }

    private Position getStartPositionForPlayer1() {
        return this.isHorizontal
            ? Position.getRandomPosition(0, this.length, 0, Config.BOARD_HEIGHT)
            : Position.getRandomPosition(0, Config.BOARD_WIDTH / 2, 0, this.length);
    }

    private Position getStartPositionForPlayer2() {
        return this.isHorizontal
            ? Position.getRandomPosition(Config.BOARD_WIDTH / 2, Config.BOARD_WIDTH - this.length, 0, Config.BOARD_HEIGHT)
            : Position.getRandomPosition(Config.BOARD_WIDTH / 2, Config.BOARD_WIDTH, 0, Config.BOARD_HEIGHT - this.length);
    }

    public Position[] getPositions() {
        return positions;
    }

    public int getColor() {
        return color;
    }
}