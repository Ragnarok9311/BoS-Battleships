package battleships.player;

import battleships.config.Config;
import battleships.player.ships.*;
import battleships.util.Adjust;

@Adjust
public class Player {

    private final String name;
    private final Ship[] ships;
    private final boolean isPlayer;
    private final int color;

    public Player(String name) {
        this(name, false, Config.SHIP_COLOR_P1);
    }

    public Player() {
        this("AI", true, Config.SHIP_COLOR_P2);
    }

    private Player(String name, boolean isPlayer, int color) {
        this.name = name;
        this.isPlayer = isPlayer;
        this.color = color;
        this.ships = this.createShips();
    }

    private Ship[] createShips() {
        return new Ship[] {
            new Battleship(this.isPlayer),
            new Cruiser(this.isPlayer),
            new Cruiser(this.isPlayer),
            new Destroyer(this.isPlayer),
            new Destroyer(this.isPlayer),
            new Destroyer(this.isPlayer),
            new Submarine(this.isPlayer),
            new Submarine(this.isPlayer),
            new Submarine(this.isPlayer),
            new Submarine(this.isPlayer),
        };
    }

    public String getName() {
        return name;
    }

    public Ship[] getShips() {
        return ships;
    }

    public int getColor() {
        return color;
    }
}
