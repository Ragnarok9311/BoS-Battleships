package battleships.core;

import battleships.display.Display;
import battleships.ships.*;
import battleships.util.Position;
import jserver.XSend;
import jserver.XSendAdapter;

public class Game {

    private final XSendAdapter adapter;
    private Ship ship;

    public Game() {
        this.adapter = new XSendAdapter();
        new Display(this.adapter);
    }

    public void start() {
        for (int i = 0; i < 1; i++) {
            this.ship = new Battleship(); // Konstantenzugriff nur über Klassennamen
            this.setShips();
        }

        for (int i = 0; i < 2; i++) {
            this.ship = new Cruiser(); // Konstantenzugriff nur über Klassennamen
            this.setShips();
        }

        for (int i = 0; i < 3; i++) {
            this.ship = new Destroyer(); // Konstantenzugriff nur über Klassennamen
            this.setShips();
        }

        for (int i = 0; i < 4; i++) {
            this.ship = new Submarine(); // Konstantenzugriff nur über Klassennamen
            this.setShips();
        }
    }

    private void setShips() {
        Position[] positions = ship.getPositions();
        for (int i = 0; i < this.ship.getLength(); i++) {
            this.adapter.farbe2(positions[i].getX(), positions[i].getY(), XSend.BLACK);
        }
    }
}