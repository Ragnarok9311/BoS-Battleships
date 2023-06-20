package battleships.display;

import battleships.config.Config;
import battleships.core.Game;
import battleships.ships.Ship;
import battleships.util.Position;
import jserver.BoardClickEvent;
import jserver.Symbol;
import jserver.XSend;
import jserver.XSendAdapterEN;

public class BoardPainter {

    private final XSendAdapterEN adapter;
    private final Game game;

    public BoardPainter(Game game, XSendAdapterEN adapter) {
        this.adapter = adapter;
        this.game = game;
        this.adapter.size(Config.BOARD_WIDTH, Config.BOARD_HEIGHT);
        this.adapter.forms(Config.FORM_SYMBOL);
        this.adapter.symbolSizes(Config.FORM_SIZE);
        this.drawShips();
    }

    public void draw(BoardClickEvent event) {
        this.clear();
        this.drawShips();
        this.printSymbolColor(event);
        this.adapter.color2(event.getX(), event.getY(), XSend.DARKRED);
    }

    private void drawShips() {
        Ship[] ships = this.game.getShips();
        for (Ship ship : ships) {
            Position[] positions = ship.getPositions();
            for (Position position : positions) {
                this.adapter.symbolSize2(position.getX(), position.getY(), Config.SHIP_FORM_SIZE);
                this.adapter.color2(position.getX(), position.getY(), Config.SHIP_COLOR);
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

    // Experimental
    private void printSymbolColor(BoardClickEvent event) {
        int linearBoardNumber = event.getX() + event.getY() * 6;
        Symbol symbol = this.adapter.board.getSymbols().get(linearBoardNumber);
        int color = this.getColorFromSymbol(symbol);
        System.out.println(color);
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
