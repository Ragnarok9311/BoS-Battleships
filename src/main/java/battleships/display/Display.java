package battleships.display;

import battleships.config.Config;
import battleships.core.Game;
import battleships.logging.Logger;
import battleships.util.Position;
import jserver.Board;
import jserver.BoardClickEvent;
import jserver.BoardClickListener;
import jserver.XSendAdapterEN;
import plotter.Graphic;

public class Display {

    private static final Logger LOGGER = Logger.getInstance();
    private static final String TAG = Display.class.getSimpleName();

    private final BoardPainter boardPainter;
    private final Game game;

    public Display(Game game, XSendAdapterEN adapter) {
        LOGGER.log("Initialize display", TAG);
        this.game = game;
        this.boardPainter = new BoardPainter(game, adapter);

        Board board = adapter.getBoard();
        board.setSize(Config.PANEL_WIDTH, Config.PANEL_HEIGHT);
        board.addClickListener(new EventListener());

        Graphic graphic = board.getGraphic();
        graphic.setTitle("Battleships");
        graphic.setLocationRelativeTo(null);
    }

    private class EventListener implements BoardClickListener {

        @Override
        public void boardClick(BoardClickEvent event) {
            if (game.isRunning() && event.getX() > Config.BOARD_WIDTH) {
                Position clickedPosition = new Position(event.getX(), event.getY());
                game.update(clickedPosition);
                boardPainter.draw(clickedPosition);
            }
        }
    }
}