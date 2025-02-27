package org.lmh.auth.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lmh.auth.application.dto.SendEmailRequestDto;
import org.lmh.auth.application.interfaces.EmailSendRepository;
import org.lmh.auth.application.interfaces.EmailVerificationRepository;
import org.lmh.auth.domain.Email;
import org.lmh.auth.domain.RandomTokenGenerator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailSendRepository emailSendRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public void sendEmail(SendEmailRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        String token = RandomTokenGenerator.generateToken();

        log.info("Sending email: {}", email.getEmailText());
        log.info("Sending email token: {}", token);

        emailSendRepository.sendVerifyEmail(email, token);
        emailVerificationRepository.createEmailVerification(email, token);
    }

    public void verifyEmail(String email, String token) {
        Email emailValue = Email.createEmail(email);
        emailVerificationRepository.verifyEmail(emailValue, token);
    }
}
