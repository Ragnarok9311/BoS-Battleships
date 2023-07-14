package battleships.player;

import battleships.core.Game;
import battleships.logging.Logger;
import battleships.player.ships.*;
import battleships.util.Adjust;

import java.util.ArrayList;
import java.util.List;

@Adjust
public class Player {

    private static final Logger LOGGER = Logger.getInstance();
    private static final String TAG = Game.class.getSimpleName();

    private final String name;
    private final List<Ship> ships;
    private final boolean isPlayer;

    public Player(String name, boolean isPlayer) {
        this.name = name;
        this.isPlayer = isPlayer;
        this.ships = this.createShips();
    }

    public Player(String name) {
        this(name, true);
    }

    public Player() {
        this("AI", false);
    }

    private List<Ship> createShips() {
        LOGGER.log("Initialize ships", TAG);
        List<Ship> ships = new ArrayList<>();
        ships.add(new Battleship(this.isPlayer));
        ships.add(new Cruiser(this.isPlayer));
        ships.add(new Cruiser(this.isPlayer));
        ships.add(new Destroyer(this.isPlayer));
        ships.add(new Destroyer(this.isPlayer));
        ships.add(new Destroyer(this.isPlayer));
        ships.add(new Submarine(this.isPlayer));
        ships.add(new Submarine(this.isPlayer));
        ships.add(new Submarine(this.isPlayer));
        ships.add(new Submarine(this.isPlayer));
        return ships;
    }

    public void removeShip(Ship ship) {
        this.ships.remove(ship);
    }

    public boolean hasLost() {
        return this.ships.isEmpty();
    }

    public String getName() {
        return name;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
