package battleships.core;

import battleships.display.Display;
import battleships.ships.Battleship;
import battleships.ships.Ship;
import battleships.util.Direction;
import battleships.util.Maths;
import jserver.XSend;
import jserver.XSendAdapter;

public class Game {

    private final XSendAdapter adapter;
    private Ship ship;

    public Game() {
        this.adapter = new XSendAdapter();
        new Display(this.adapter);

        for (int i = 0; i < 20; i++) {
            this.ship = new Battleship(this.getRanmdomDirection()); // Konstantenzugriff nur über Klassennamen
            this.setShips();
        }
    }

    private Direction getRanmdomDirection() {
        return Maths.getRandomNumberBetween(0, 1) == 0 ? Direction.HORIZONTAL : Direction.VERTICAL;
    }

    private void setShips() {
        int startX = this.ship.getStartPos().getX();
        int startY = this.ship.getStartPos().getY();

        if (this.ship.getDirection() == Direction.HORIZONTAL) {
            for (int i = 0; i < this.ship.getLength(); i++) {
                this.adapter.farbe2(
                    startX++,
                    startY,
                    XSend.GRAY
                );
            }
        } else {
            for (int i = 0; i < this.ship.getLength(); i++) {
                this.adapter.farbe2(
                    startX,
                    startY++,
                    XSend.GRAY
                );
            }
        }
    }
}