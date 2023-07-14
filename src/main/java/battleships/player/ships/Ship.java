package battleships.player.ships;

import battleships.config.Config;
import battleships.core.BattleshipsBoard;
import battleships.util.Maths;
import battleships.util.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship {

    protected int length;
    protected boolean isPlayer;
    protected boolean isHorizontal;
    protected int colorValue;
    protected List<Position> positions;
    protected List<Position> destroyedPostions;

    public Ship(int length, boolean isPlayer) {
        this.length = length;
        this.isPlayer = isPlayer;
        this.colorValue = this.isPlayer ? Config.SHIP_COLOR_P1 : Config.SHIP_COLOR_P2;
//        this.colorValue = Maths.getRandomColorValue();
        this.positions = new ArrayList<>(length);
        this.destroyedPostions = new ArrayList<>(length);
        this.addPositions();
    }

    private void addPositions() {
        this.isHorizontal = Maths.getRandomBoolean();
        this.positions.add(this.getRandomStartPosition());
        int x = this.positions.get(0).getX();
        int y = this.positions.get(0).getY();

        for (int i = 1; i < this.length; i++) {
            if (this.isHorizontal) {
                x++;
            } else {
                y++;
            }
            this.positions.add(new Position(x, y));
        }
        this.checkAvailablePositions();
    }

    private void checkAvailablePositions() {
        List<Position> boardPositions = BattleshipsBoard.BOARD;
        List<Position> tempList = new ArrayList<>();

        boolean hasShipValidPositions = true;
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
                hasShipValidPositions = false;
                break;
            }
        }

        if (hasShipValidPositions) return;
        boardPositions.addAll(tempList);
        this.positions.clear();
        this.addPositions();
    }

    private Position getRandomStartPosition() {
        return this.isPlayer ? this.getStartPositionForPlayerShip() : this.getStartPositionForAIShip();
    }

    private Position getStartPositionForPlayerShip() {
        return this.isHorizontal
                ? Position.getRandomPosition(0, this.length + 1, 0, Config.BOARD_HEIGHT)
                : Position.getRandomPosition(0, Config.BOARD_WIDTH, 0, this.length + 1);
    }

    private Position getStartPositionForAIShip() {
        return this.isHorizontal
                ? Position.getRandomPosition(Config.BOARD_WIDTH + 1, Config.BOARD_WIDTH * 2 - this.length + 2, 0, Config.BOARD_HEIGHT)
                : Position.getRandomPosition(Config.BOARD_WIDTH + 1, Config.BOARD_WIDTH * 2 - this.length + 2, 0, Config.BOARD_HEIGHT - this.length);
    }

    public void removePosition(Position position) {
        this.positions.remove(position);
        this.destroyedPostions.add(position);
    }

    public boolean isDestroyed() {
        return this.positions.isEmpty();
    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<Position> getDestroyedPostions() {
        return destroyedPostions;
    }

    public int getColorValue() {
        return colorValue;
    }
}