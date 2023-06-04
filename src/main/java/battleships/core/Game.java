package battleships.core;

import battleships.config.Config;
import battleships.display.Display;
import battleships.ships.Battleship;
import battleships.ships.Ship;
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

    public Ship[] getShips() {
        return ships;
    }
}