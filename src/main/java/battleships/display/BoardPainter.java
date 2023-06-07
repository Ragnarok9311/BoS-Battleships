package battleships.display;

import battleships.config.Config;
import battleships.core.Game;
import battleships.ships.Ship;
import battleships.util.Position;
import jserver.BoardClickEvent;
import jserver.XSend;
import jserver.XSendAdapter;

public class BoardPainter {

    private final XSendAdapter adapter;
    private final Game game;

    public BoardPainter(Game game, XSendAdapter adapter) {
        this.adapter = adapter;
        this.game = game;
        this.adapter.groesse(Config.BOARD_WIDTH, Config.BOARD_HEIGHT);
        this.initDraw();
    }

    public void draw(BoardClickEvent event) {
        this.clear();
        this.adapter.farbe2(event.getX(), event.getY(), XSend.DARKRED);
    }

    private void initDraw() {
        Ship[] ships = this.game.getShips();
        for (Ship ship : ships) {
            Position[] positions = ship.getPositions();
            for (Position position : positions) {
                this.adapter.farbe2(position.getX(), position.getY(), XSend.GRAY);
            }
        }
    }

    private void clear() {
        for (int y = 0; y < Config.BOARD_HEIGHT; y++) {
            for (int x = 0; x < Config.BOARD_WIDTH; x++) {
                this.adapter.farbe2(x, y, Config.BOS_DEFAULT_GRAY);
            }
        }
    }
}
