package battleships.display;

import battleships.config.Config;
import battleships.core.Game;
import jserver.*;
import plotter.Graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {

    // Instanzvariablen, Objektvariablen (Eigenschaften eines Objekts)
    private final BoardPainter boardPainter;

    // Konstruktor
    public Display(Game game, XSendAdapter adapter) {
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
            boardPainter.draw(event);
        }

        @Override
        public void actionPerformed(ActionEvent event) {

        }
    }
}