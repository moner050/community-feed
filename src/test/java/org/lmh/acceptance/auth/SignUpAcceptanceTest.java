package org.lmh.acceptance.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lmh.acceptance.utils.AcceptanceTestTemplate;
import org.lmh.auth.application.dto.CreateUserAuthRequestDto;
import org.lmh.auth.application.dto.SendEmailRequestDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.lmh.acceptance.steps.SignUpAcceptanceSteps.*;

public class SignUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "test@email.com";

    @BeforeEach
    void setup() {
        super.cleanUp();
    }

    @Test
    void givenEmail_whenSendEmail_thenVerificationTokenSaved() {
        // given
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        // when
        Integer code = requestSendEmail(dto);

        // then
        String token = this.getEmailToken(email);

        assertNotNull(token);
        assertEquals(0, code);
    }

    @Test
    void givenInvalidEmail_whenEmailSend_thenVerificationTokenNotSaved() {
        // given
        SendEmailRequestDto dto = new SendEmailRequestDto("not");

        // when
        Integer code = requestSendEmail(dto);

        // then
        assertEquals(400, code);
    }

    @Test
    void givenSendEmail_whenVerifyEmail_thenEmailVerified() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        String token = getEmailToken(email);
        Integer code = requestVerifyEmail(email, token);

        // then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(0, code);
        assertTrue(isEmailVerified);
    }


    @Test
    void givenSendEmail_whenVerifyEmailWithWrongToken_thenEmailNotVerified() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        Integer code = requestVerifyEmail(email, "wrong token");

        // then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(400, code);
        assertFalse(isEmailVerified);
    }

    @Test
    void givenSendEmailVerified_whenVerifyAgain_thenThrowError() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = this.getEmailToken(email);
        requestVerifyEmail(email, token);

        // when
        Integer code = requestVerifyEmail(email, token);

        // then
        assertEquals(400, code);
    }

    @Test
    void givenSendEmail_whenVerifyEmailWithWrongEmail_thenThrowError() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        Integer code = requestVerifyEmail("wrong email", "token");

        // then
        assertEquals(400, code);
    }

    @Test
    void givenVerifiedEmail_whenRegister_thenUserRegistered() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);

        // when
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto(email, "password", "USER", "name", "profileImageUrl");
        Integer code = registerUser(dto);

        // then
        assertEquals(0, code);
        Long userId = getUserId(email);
        assertEquals(1L, userId);
    }

    @Test
    void givenUnVerifiedEmail_whenRegister_thenThrowError() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto(email, "password", "USER", "name", "profileImageUrl");
        Integer code = registerUser(dto);

        // then
        assertEquals(400, code);
    }


}
