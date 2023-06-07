package battleships.core;

import battleships.display.Display;
import battleships.logging.Logger;
import battleships.ships.*;
import jserver.XSendAdapter;

public class Game {

    private static final String TAG = Game.class.getSimpleName();

    private Ship[] ships;

    public Game() {
        Logger.getLogger().log("Initialize Game", TAG);
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