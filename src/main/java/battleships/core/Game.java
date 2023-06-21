package battleships.core;

import battleships.config.Config;
import battleships.display.Display;
import battleships.logging.Logger;
import battleships.ships.*;
import battleships.util.Position;
import jserver.XSendAdapterEN;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final List<Position> BOARD_POSITIONS = new ArrayList<>();

    private static final Logger LOGGER = Logger.getInstance();
    private static final String TAG = Game.class.getSimpleName();

    private Ship[] playerShips;

    public Game() {
        LOGGER.log("Initialize game", TAG);
        this.initBoard();
        this.initShips();
        new Display(this, new XSendAdapterEN());
    }

    private void initBoard(){
        LOGGER.log("Initialize board positions list", TAG);
        for (int x = 0; x < Config.BOARD_WIDTH; x++){
            for (int y = 0; y < Config.BOARD_HEIGHT; y++){
                BOARD_POSITIONS.add(new Position(x, y));
            }
        }
    }

    private void initShips() {
        LOGGER.log("Initialize ships", TAG);
        this.playerShips = new Ship[] {
            new Battleship(true),
            new Cruiser(true),
            new Cruiser(true),
            new Destroyer(true),
            new Destroyer(true),
            new Destroyer(true),
            new Submarine(true),
            new Submarine(true),
            new Submarine(true),
            new Submarine(true),

            new Battleship(false),
            new Cruiser(false),
            new Cruiser(false),
            new Destroyer(false),
            new Destroyer(false),
            new Destroyer(false),
            new Submarine(false),
            new Submarine(false),
            new Submarine(false),
            new Submarine(false)
        };
    }

    public Ship[] getPlayerShips() {
        return playerShips;
    }
}