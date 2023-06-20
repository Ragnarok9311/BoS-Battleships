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



    public Ship(int length) {
        this.length = length;
        this.setHorizontal();
        this.positions = new Position[length];
        this.setPositions();

    }

    private void setHorizontal() {
        this.isHorizontal = Maths.getRandomBoolean();
    }
    private void setPositions(){
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
    private void checkAvailablePositions(){
        List<Position> boardPositions = Game.BOARD_POSITIONS;
        List<Position> tempList = new ArrayList<>();

        boardPositions.forEach(position -> {
            for(Position shipPosition : this.positions) {
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

    private boolean isValidPosition(Position position, Position shipPosition) {
        return shipPosition.getX() == position.getX() && shipPosition.getY() == position.getY();
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