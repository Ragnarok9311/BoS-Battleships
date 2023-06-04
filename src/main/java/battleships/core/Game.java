package battleships.core;

import battleships.display.Display;
import battleships.ships.Battleship;
import battleships.ships.Ship;
import battleships.util.Position;
import jserver.XSend;
import jserver.XSendAdapter;

public class Game {

    private final XSendAdapter adapter;
    private Ship ship;

    public Game() {
        this.adapter = new XSendAdapter();
        new Display(this.adapter);

        for (int i = 0; i < 5; i++) {
            this.ship = new Battleship(); // Konstantenzugriff nur über Klassennamen
            this.setShips();
        }
    }

    private void setShips() {
        Position startPos = this.ship.getStartPos();
        int startX = startPos.getX();
        int startY = startPos.getY();

        if (this.ship.isHorizontal()) {
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