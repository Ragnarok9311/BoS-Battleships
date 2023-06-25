package battleships.core;

import battleships.config.Config;
import battleships.display.Display;
import battleships.logging.Logger;
import battleships.player.ships.*;
import battleships.util.Position;
import jserver.BoardClickEvent;
import jserver.XSendAdapterEN;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final List<Position> BOARD_POSITIONS = new ArrayList<>();

    private static final Logger LOGGER = Logger.getInstance();
    private static final String TAG = Game.class.getSimpleName();

    private Ship[] playerShips;
    private boolean isPlayer1sTurn;

    public Game() {
        LOGGER.log("Initialize game", TAG);
        isPlayer1sTurn = true;
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

    public void update(BoardClickEvent event) {
        if (this.isPlayer1sTurn) {
            if (event.getX() >= 10) {
                this.isHit(new Position(event.getX(), event.getY()));
            }
        }
    }

    public void isHit(Position clickPosition) {
        for (Ship ship : playerShips) {
            for (Position shipPosition : ship.getPositions()) {
                if (clickPosition.equals(shipPosition)) {
                    System.out.println("Ship is hit");
                }
            }
        }
    }
    public Ship[] getPlayerShips() {
        return playerShips;
    }
}