package battleships.display;

import jserver.BoardClickEvent;
import jserver.BoardClickListener;
import jserver.XSend;
import jserver.XSendAdapter;
import plotter.Graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Display {

    private final XSendAdapter adapter; //Eigenschaft Adapter, Instanzvariablen, Objektvariablen

    public Display() {
        this.adapter = new XSendAdapter();
        adapter.board.addClickListener(new EventHandler());
        //adapter.farbe2(2, 2, XSend.DARKRED);
        Graphic graphic = adapter.board.getGraphic();
        graphic.setTitle("Battleships");
        graphic.setLocationRelativeTo(null); //null = Literal

    }

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