package battleships.config;

import jserver.XSend;

public final class Config {

    public static final int PANEL_WIDTH = 1200;                 // Default: 1200
    public static final int PANEL_HEIGHT = 600;                 // Default: 600
    public static final int PANEL_BACKGROUND_COLOR = 0xEEEEEE;
    public static final int BOARD_WIDTH = 10;                   // Default: 20
    public static final int BOARD_HEIGHT = 10;                  // Default: 10

    public static final int BOS_DEFAULT_GRAY = XSend.SILVER;
    public static final int FORM_COLOR = XSend.DODGERBLUE;
    public static final int SHIP_COLOR_P1 = XSend.GRAY;
    public static final int SHIP_COLOR_P2 = XSend.SILVER;
    public static final double FORM_SIZE = 0.48;
    public static final double SHIP_FORM_SIZE = 0.5;
    public static final String FORM_SYMBOL = "s";

    private Config() {
        throw new UnsupportedOperationException();
    }
}