package org.lmh.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.lmh.auth.domain.Password;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue() {
        // given
        Password password = Password.createEncryptPassword("password");

        // when && then
        assertTrue(password.matchPassword("password"));
    }

    @Test
    void givenPassword_whenMatchDiffPassword_thenReturnFalse() {
        // given
        Password password = Password.createEncryptPassword("password1");

        // when && then
        assertFalse(password.matchPassword("password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordIsNull_thenThrowError(String password) {
        assertThrows(IllegalArgumentException.class, () -> Password.createEncryptPassword(password));
    }
}
