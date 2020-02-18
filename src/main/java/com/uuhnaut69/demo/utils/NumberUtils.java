package com.uuhnaut69.demo.utils;

import java.util.Random;

public final class NumberUtils {

    private NumberUtils() {

    }


    /**
     * Get random number from 0->3
     *
     * @return int
     */
    public static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(3);
    }
}
