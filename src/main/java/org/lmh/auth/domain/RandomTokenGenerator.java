package org.lmh.auth.domain;

import java.security.SecureRandom;

public class RandomTokenGenerator {

    private static final String CHARACTERS = "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZabcedfghijklmnopqrstuvwxyz";
    private static final int TOKEN_LENGTH = 16;
    private static final SecureRandom random = new SecureRandom();

    private RandomTokenGenerator() {

    }

    public static String generateToken() {
        StringBuilder token = new StringBuilder(TOKEN_LENGTH);

        for (int i = 0; i < 16; i++) {
            token.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return token.toString();
    }
}
