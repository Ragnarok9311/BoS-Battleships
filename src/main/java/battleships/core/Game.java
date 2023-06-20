package battleships.core;

import battleships.config.Config;
import battleships.display.Display;
import battleships.logging.Logger;
import battleships.ships.*;
import battleships.util.Position;
import jserver.XSendAdapter;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final List<Position> BOARD_POSITIONS = new ArrayList<>();
    private static final String TAG = Game.class.getSimpleName();

    private Ship[] ships;

    public Game() {
        Logger.getLogger().log("Initialize Game", TAG);
        initBoard();
        this.initShips();
        new Display(this, new XSendAdapter());
    }

    private void initBoard(){
        for (int x = 0; x < Config.BOARD_WIDTH; x++){
            for (int y = 0; y < Config.BOARD_HEIGHT; y++){
                BOARD_POSITIONS.add(new Position(x, y));
            }
        }
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