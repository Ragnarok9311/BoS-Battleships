package battleships.util;

import java.util.Random;

public final class Maths {

    private static final Random RANDOM = new Random();

    private Maths() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return random number between {@code min} inclusive and {@code max} inclusive
     */
    public static int getRandomNumberBetween(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static boolean getRandomBoolean() {
        return RANDOM.nextBoolean();
    }
}