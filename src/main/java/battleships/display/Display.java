package battleships.display;

import battleships.config.Config;
import battleships.core.Game;
import battleships.logging.Logger;
import jserver.*;
import plotter.Graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {

    // Statische Variablen, Konstanten oder Klassenvariablen
    private static final Logger LOGGER = Logger.getInstance();
    private static final String TAG = Display.class.getSimpleName();

    // Instanzvariablen, Objektvariablen (Eigenschaften eines Objekts)
    private final BoardPainter boardPainter;
    private final Game game;

    // Konstruktor
    public Display(Game game, XSendAdapterEN adapter) {
        LOGGER.log("Initialize display", TAG);
        this.game = game;
        this.boardPainter = new BoardPainter(game, adapter);

        Board board = adapter.getBoard();
        board.setSize(Config.PANEL_WIDTH, Config.PANEL_HEIGHT);
        board.addClickListener(new EventHandler());

        Graphic graphic = board.getGraphic();
        graphic.setTitle("Battleships");
        graphic.setLocationRelativeTo(null); // null = Literal
    }

    // Innere Klasse
    private class EventHandler implements BoardClickListener, ActionListener {

        @Override
        public void boardClick(BoardClickEvent event) {
            game.update(event);
            boardPainter.draw(event);
        }

        @Override
        public void actionPerformed(ActionEvent event) {

        }
    }
}