package org.lmh.auth;

import org.junit.jupiter.api.Test;
import org.lmh.auth.domain.RandomTokenGenerator;

import static org.junit.jupiter.api.Assertions.*;

class RandomTokenGenerateTest {

    @Test
    void whenGenerateToken_thenReturnTokenWithCorrectLength() {
        // when
        String token = RandomTokenGenerator.generateToken();

        // then
        assertNotNull(token);
        assertEquals(16, token.length());
    }

    @Test
    void whenGenerateToken_thenReturnTokenWithValidCharacters() {
        // when
        String token = RandomTokenGenerator.generateToken();

        // then
        assertNotNull(token);
        assertTrue(token.matches("[0-9A-Za-z]{16}"));
    }
}
