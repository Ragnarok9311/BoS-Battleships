package battleships.display;

import jserver.*;
import plotter.Graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {

    // Instanzvariablen, Objektvariablen (Eigenschaften)
    private final XSendAdapter adapter;

    // Konstruktor
    public Display() {
        this.adapter = new XSendAdapter();

        Board board = this.adapter.getBoard();
        board.addClickListener(new EventHandler());

        Graphic graphic = board.getGraphic();
        graphic.setTitle("Battleships");
        graphic.setLocationRelativeTo(null); // null = Literal
    }

    // Innere Klasse
    private class EventHandler implements BoardClickListener, ActionListener {

        @Override
        public void boardClick(BoardClickEvent event) {
            adapter.farbe2(event.getX(), event.getY(), XSend.DARKRED);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}