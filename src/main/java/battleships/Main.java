package battleships;

import battleships.core.BattleshipsBoard;
import battleships.core.Game;

public class Main {

    public static void main(String[] args) {
        new Game(new BattleshipsBoard());
    }
}