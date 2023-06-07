package battleships.config;

import jserver.XSend;

public final class Config {

    public static final int BOARD_WIDTH = 20;
    public static final int BOARD_HEIGHT = 10;
    public static final int NUMBER_OF_SHIPS = 5;
    public static final int BOS_DEFAULT_GRAY = XSend.DARKGRAY;

    private Config() {
        throw new UnsupportedOperationException();
    }
}