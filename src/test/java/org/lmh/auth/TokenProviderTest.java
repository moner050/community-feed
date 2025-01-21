package org.lmh.auth;

import org.junit.jupiter.api.Test;
import org.lmh.auth.domain.TokenProvider;

import static org.junit.jupiter.api.Assertions.*;

class TokenProviderTest {

    private final String secretKey = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);

    @Test
    void givenValidUserAndRole_whenCreateToken_thenReturnValidToken() {
        // given
        Long userId = 1L;
        String role = "ADMIN";

        // when
        String token = tokenProvider.createToken(userId, role);

        // then
        assertNotNull(token);
        assertEquals(userId, tokenProvider.getUserId(token));
        assertEquals(role, tokenProvider.getUserRole(token));
    }

    @Test
    void givenInvalidToken_whenGetGetUserId_thenThrowError() {
        // given
        String invalidToken = "invalidToken";

        // when&then
        assertThrows(Exception.class, () -> tokenProvider.getUserId(invalidToken));
    }
}
