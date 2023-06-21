package battleships.display;

import battleships.config.Config;
import battleships.core.Game;
import battleships.logging.Logger;
import battleships.ships.Ship;
import battleships.util.Position;
import jserver.BoardClickEvent;
import jserver.Symbol;
import jserver.XSend;
import jserver.XSendAdapterEN;

public class BoardPainter {

    private static final String TAG = BoardPainter.class.getSimpleName();
    private static final Logger LOGGER = Logger.getInstance();

    private final XSendAdapterEN adapter;
    private final Game game;

    public BoardPainter(Game game, XSendAdapterEN adapter) {
        LOGGER.log("Initialize BoardPainter", TAG);
        this.adapter = adapter;
        this.game = game;
        this.initBoard();
        this.drawShips();
    }

    public void draw(BoardClickEvent event) {
        this.clear();
        this.adapter.colors(Config.FORM_COLOR);
        this.drawShips();
        this.printSymbolColor(event);
        this.adapter.text2(event.getX(), event.getY(), "X");
        this.adapter.textColor2(event.getX(), event.getY(), XSend.DARKRED);
    }

    private void drawShips() {
        Ship[] ships = this.game.getPlayerShips();
        for (Ship ship : ships) {
            Position[] positions = ship.getPositions();
            for (Position position : positions) {
                this.adapter.symbolSize2(position.getX(), position.getY(), Config.SHIP_FORM_SIZE);
                this.adapter.color2(position.getX(), position.getY(), ship.getColor());
            }
        }
    }

    private void clear() {
        for (int y = 0; y < Config.BOARD_HEIGHT; y++) {
            for (int x = 0; x < Config.BOARD_WIDTH; x++) {
                this.adapter.color2(x, y, Config.BOS_DEFAULT_GRAY);
            }
        }
    }

    private void initBoard() {
        this.adapter.size(Config.BOARD_WIDTH, Config.BOARD_HEIGHT);
        this.adapter.forms(Config.FORM_SYMBOL);
        this.adapter.colors(Config.FORM_COLOR);
        this.adapter.symbolSizes(Config.FORM_SIZE);
    }

    // Experimental
    private void printSymbolColor(BoardClickEvent event) {
        int linearBoardNumber = event.getX() + event.getY() * 6;
        Symbol symbol = this.adapter.board.getSymbols().get(linearBoardNumber);
        int color = this.getColorFromSymbol(symbol);
        LOGGER.debug("Symbol color from click: " + color, TAG);
    }

    private int getColorFromSymbol(Symbol symbol) {
        int r = symbol.getFarbe().getRed();
        int g = symbol.getFarbe().getGreen();
        int b = symbol.getFarbe().getBlue();
        r = 256 * 256 * r;
        g = 256 * g;
        return r + g + b;
    }
}
