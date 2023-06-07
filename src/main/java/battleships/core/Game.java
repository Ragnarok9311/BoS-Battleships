package battleships.core;

import battleships.display.Display;
import battleships.ships.*;
import jserver.XSendAdapter;

public class Game {
  
    private Ship[] ships;

    public Game() {
        this.initShips();
        new Display(this, new XSendAdapter());
    }

    private void initShips() {
        this.ships = new Ship[] {
            new Battleship(),
            new Cruiser(),
            new Cruiser(),
            new Destroyer(),
            new Destroyer(),
            new Destroyer(),
            new Submarine(),
            new Submarine(),
            new Submarine(),
            new Submarine()
        };
    }

    public Ship[] getShips() {
        return ships;
    }
}