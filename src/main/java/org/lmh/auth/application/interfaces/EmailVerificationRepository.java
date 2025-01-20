package org.lmh.auth.application.interfaces;

import org.lmh.auth.domain.Email;

public interface EmailVerificationRepository {
    void createEmailVerification(Email email, String token);
}
