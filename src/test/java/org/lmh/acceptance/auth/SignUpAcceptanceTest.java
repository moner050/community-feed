package org.lmh.acceptance.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lmh.acceptance.utils.AcceptanceTestTemplate;
import org.lmh.auth.application.dto.SendEmailRequestDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.lmh.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;

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
}
