package battleships.core;

import battleships.config.Config;
import battleships.util.Position;

import java.util.ArrayList;
import java.util.List;

public class BattleshipsBoard {

    public static final List<Position> PLAYER_BOARD = new ArrayList<>(Config.BOARD_WIDTH * Config.BOARD_HEIGHT);
    public static final List<Position> AI_BOARD = new ArrayList<>(Config.BOARD_WIDTH * Config.BOARD_HEIGHT);
    public static final List<Position> BOARD = new ArrayList<>((Config.BOARD_WIDTH * Config.BOARD_HEIGHT) * 2);

    public BattleshipsBoard() {
        this.initBoards();
        BOARD.addAll(PLAYER_BOARD);
        BOARD.addAll(AI_BOARD);
    }

    private void initBoards() {
        // Positions for player side
        for (int x = 0; x < Config.BOARD_WIDTH; x++){
            for (int y = 0; y < Config.BOARD_HEIGHT; y++){
                PLAYER_BOARD.add(new Position(x, y));
            }
        }

        // Positions for ai side
        for (int x = Config.BOARD_WIDTH + 1; x < Config.BOARD_WIDTH * 2 + 1; x++) {
            for (int y = 0; y < Config.BOARD_HEIGHT; y++) {
                AI_BOARD.add(new Position(x, y));
            }
        }
    }
}