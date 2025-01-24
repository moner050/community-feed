package org.lmh.auth.application.interfaces;

import org.lmh.auth.domain.Email;

public interface EmailSendRepository {

    void sendEmail(String toMail, String title, String content);
    void sendVerifyEmail(Email email, String token);
}
