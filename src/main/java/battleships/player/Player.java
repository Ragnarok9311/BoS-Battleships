package battleships.player;

import battleships.player.ships.*;
import battleships.util.Adjust;

@Adjust
public class Player {

    private String name;
    private Ship[] ships;

    public Player(String name) {
        this.name = name;
        this.ships = new Ship[]{
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
        };
    }

    public Player() {
        this.name = "AI";
    }
}
