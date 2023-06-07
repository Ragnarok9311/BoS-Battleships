package battleships.core;

import battleships.config.Config;
import battleships.display.Display;
import battleships.ships.*;
import battleships.util.Position;
import jserver.XSend;
import jserver.XSendAdapter;

public class Game {
  
    private final Ship[] ships;

    public Game() {
        this.ships = new Ship[Config.NUMBER_OF_SHIPS];
        this.initShips();
        new Display(this, new XSendAdapter());
    }

    private void initShips() {
        for (int i = 0; i < this.ships.length; i++) {
            this.ships[i] = new Battleship(); // Konstantenzugriff nur über Klassennamen
        }
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

    public Ship[] getShips() {
        return ships;
    }
}