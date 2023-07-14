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

import javax.swing.*;
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

        EventListener eventListener = new EventListener();
        Board board = adapter.getBoard();
        board.setSize(Config.PANEL_WIDTH, Config.PANEL_HEIGHT);
        board.addClickListener(eventListener);

        Graphic graphic = board.getGraphic();
        graphic.setTitle("Battleships");
        graphic.setLocationRelativeTo(null); // null = Literal

        this.initButtons(graphic, eventListener);
    }

    private void initButtons(Graphic graphic, EventListener eventListener) {
        JButton button = new JButton("Zug beenden");
        button.addActionListener(eventListener);
        graphic.addBottomComponent(button);
    }

    // Innere Klasse
    private class EventListener implements BoardClickListener, ActionListener {

        @Override
        public void boardClick(BoardClickEvent event) {
            if (game.isRunning() && event.getX() < Config.BOARD_WIDTH) {
                Position clickedPosition = new Position(event.getX(), event.getY());
                game.update(clickedPosition);
                boardPainter.draw(clickedPosition);
            }
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("Button clicked");
        }
    }
}