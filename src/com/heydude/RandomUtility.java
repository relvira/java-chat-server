package com.heydude;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class RandomUtility {
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * @return Siguiente aleatorio alphanumérico.
     */
    public static String nextId() {
        return new BigInteger(130, RANDOM).toString(32);
    }

    /**
     * @return String generada aleatoriamente.
     */
    public static String randomString() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
