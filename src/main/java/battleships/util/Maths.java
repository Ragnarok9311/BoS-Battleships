package battleships.util;

import java.util.Random;

public final class Maths {

    private static final Random RANDOM = new Random();

    private Maths() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return random number between {@code min} inclusive and {@code max} exclusive
     */
    public static int getRandomNumberBetween(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static boolean getRandomBoolean() {
        return RANDOM.nextBoolean();
    }

    public static int getRandomColorValue() {
        return (int) (Math.random() * 256 * 256 * 256);
    }
}