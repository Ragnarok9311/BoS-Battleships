package battleships.display;

import battleships.config.Config;
import battleships.core.Game;
import battleships.logging.Logger;
import battleships.player.ships.*;
import battleships.util.Position;
import jserver.XSend;
import jserver.XSendAdapterEN;

public class BoardPainter {

    private static final String TAG = BoardPainter.class.getSimpleName();
    private static final Logger LOGGER = Logger.getInstance();

    private final XSendAdapterEN adapter;
    private final Game game;

    public BoardPainter(Game game, XSendAdapterEN adapter) {
        LOGGER.log("Initialize board painter", TAG);
        this.adapter = adapter;
        this.game = game;
        this.initBoard();
        this.drawPlayerShips();
        this.drawAiShips();
    }

    private void initBoard() {
        this.adapter.size(Config.BOARD_WIDTH * 2 + 1, Config.BOARD_HEIGHT);
        this.adapter.forms(Config.FORM_SYMBOL);
        this.adapter.colors(Config.FORM_COLOR);
        this.adapter.symbolSizes(Config.FORM_SIZE);
        this.adapter.statusText("Battleships: 1 | Cruisers: 2 | Destroyers: 3 | Submarines: 4");
        this.drawPartingLine();
    }

    private void drawPartingLine() {
        for (int y = 0; y < Config.BOARD_HEIGHT; y++) {
            this.adapter.color2(Config.BOARD_WIDTH, y, Config.PANEL_BACKGROUND_COLOR);
        }
    }

    public void draw(Position clickedPosition) {
        this.clear();
        this.drawPlayerShips();
        this.drawAiShips();
        this.drawHits(clickedPosition);
        this.updateStatusText();
    }

    private void clear() {
        for (int y = 0; y < Config.BOARD_HEIGHT; y++) {
            for (int x = 0; x < Config.BOARD_WIDTH * 2 + 1; x++) {
                this.adapter.symbolSize2(x, y, Config.FORM_SIZE);
                this.adapter.color2(x, y, Config.FORM_COLOR);
            }
        }
        this.drawPartingLine();
    }

    private void drawPlayerShips() {
        for (Ship ship : this.game.getPlayer().getShips()) {
            for (Position position : ship.getPositions()) {
                this.adapter.symbolSize2(position.getX(), position.getY(), Config.SHIP_FORM_SIZE);
                this.adapter.color2(position.getX(), position.getY(), ship.getColorValue());
            }

            for (Position position : ship.getDestroyedPostions()) {
                this.adapter.symbolSize2(position.getX(), position.getY(), Config.SHIP_FORM_SIZE);
                this.adapter.color2(position.getX(), position.getY(), ship.getColorValue());
            }
        }
    }

    private void drawAiShips() {
        for (Ship ship : this.game.getAI().getShips()) {
            for (Position position : ship.getDestroyedPostions()) {
                this.adapter.symbolSize2(position.getX(), position.getY(), Config.SHIP_FORM_SIZE);
                this.adapter.color2(position.getX(), position.getY(), ship.getColorValue());
            }
        }
    }

    private void drawHits(Position clickedPosition) {
        this.adapter.text2(clickedPosition.getX(), clickedPosition.getY(), "X");
        this.adapter.textColor2(clickedPosition.getX(), clickedPosition.getY(), XSend.WHITE);

        Position hitPositionByAi = this.game.getHitPositionByAi();
        if (hitPositionByAi != null) {
            this.adapter.text2(hitPositionByAi.getX(), hitPositionByAi.getY(), "X");
            this.adapter.textColor2(hitPositionByAi.getX(), hitPositionByAi.getY(), XSend.WHITE);
        }
    }

    private void updateStatusText() {
        byte[] shipCounter = new byte[4];
        for (Ship ship : this.game.getAI().getShips()) {
            if (ship instanceof Battleship) shipCounter[0]++;
            else if (ship instanceof Cruiser) shipCounter[1]++;
            else if (ship instanceof Destroyer) shipCounter[2]++;
            else if (ship instanceof Submarine) shipCounter[3]++;
        }
        this.adapter.statusText(String.format("Battleships: %d | Cruisers: %d | Destroyers: %d | Submarines: %d",
                shipCounter[0], shipCounter[1], shipCounter[2], shipCounter[3]));
    }
}