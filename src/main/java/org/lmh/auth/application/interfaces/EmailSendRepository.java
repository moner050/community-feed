package org.lmh.auth.application.interfaces;

import org.lmh.auth.domain.Email;

public interface EmailSendRepository {

    void sendEmail(Email email, String token);
}
